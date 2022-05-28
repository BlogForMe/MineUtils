package com.comm.util.ndk

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.android.util.ToastUtil
import com.comm.util.DevicePort
import com.comm.util.R
import com.comm.util.ndk.ParseZigUtil.getCurrentPress
import com.comm.util.utils.GsonUtil
import com.comm.util.utils.StringUtil.decimalOnePlace
import com.comm.util.utils.StringUtil.decimalTwoPlace
import kotlinx.android.synthetic.main.activity_jyso.*
import timber.log.Timber
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class JYSoActivity : AppCompatActivity() {

    val ALLOW_JOHIN = "ALLOW_JOIN"
    val SET = "SET"
    val DEVICE_JOIN = "DEVICE_JOIN"
    val REPORT = "REPORT"

    val MODEL_TRIANGLE = "113" // 三合一设备
    val MODEL_ALARM = "83"
    val MODEL_PC_ADAPTER = "228" //适配器
    val MODEL_PC_200 = "114" //适配器

    private val ALLOW_JOIN = "{\n" +
            "    \"cmd\":\"ALLOW_JOIN\",\n" +
            "    \"model\":\"240\",\n" +
            "    \"sid\":\"\",\n" +
            "    \"short_addr\":0,\n" +
            "    \"endpoint\":0,\n" +
            "    \"data\":null\n" +
            "}"

    val DISABLE_JOIN = "{\n" +
            "    \"cmd\":\"DISABLE_JOIN\",\n" +
            "    \"model\":\"240\",\n" +
            "    \"sid\":\"\",\n" +
            "    \"short addr\":0,\n" +
            "    \"endpoint\":0,\n" +
            "    \"data\":null\n" +
            "}"

    val RESET = "{\n" +
            "    \"cmd\":\"RESET\",\n" +
            "    \"model\":\"240\",\n" +
            "    \"sid\":\"\",\n" +
            "    \"short addr\":0,\n" +
            "    \"endpoint\":0,\n" +
            "    \"data\":null\n" +
            "}"

    val handler = MyHandler(this)

    class MyHandler : Handler {

        private var mActivity: WeakReference<JYSoActivity>

        constructor(activity: JYSoActivity) {
            mActivity = WeakReference(activity)
        }
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var activity = mActivity.get()
            val ssCha = msg.obj as String
            val zigBean = GsonUtil.parseData(ssCha)
            if (zigBean == null) {
                return
            }
            when (zigBean.cmd) {
                activity?.DEVICE_JOIN -> {
//                    ToastUtils.showLongToast("设备加网 设备id ${zigBean.model}")
                    Timber.i("设备加网 设备id ${zigBean.model}")
                    when (zigBean.model) {
                        activity?.MODEL_PC_ADAPTER -> {
//                            val isSave = SharedPrefsUtils.setZig(zigBean)
//                            Timber.i("数据已存储 $isSave")
                        }
                    }
                }
                activity?.REPORT -> {
                    when (zigBean.model) {
                        activity?.MODEL_ALARM ->
                            ToastUtil.showShort(activity?.getText(R.string.alarm_key) as String?)
                        activity?.MODEL_TRIANGLE -> {
                            val suData = zigBean.data
                            suData?.let { it ->
                                var spacess = ParseZigUtil.hexToByteArray(it.data)
                                val deBean = ParseZigUtil.modifyDeviceState(spacess)
                                deBean?.let { it ->
                                    if (it.isCheckGls) {
                                        activity?.tv_show?.text = (decimalTwoPlace(it.dataValue / 18.0)).toString()
                                    } else {
                                        activity?.tv_show?.text = "高压 ${deBean.pressureHighValue} mmHg  ,低压 ${deBean.pressureLowValue} mmHg, 脉搏${deBean.jumpValue}  "
                                    }
                                }
                            }
                        }
                        activity?.MODEL_PC_200 -> {
                            val dataCheck = zigBean.data?.data

                            when (zigBean.data?.cmdID) {
                                "114" -> { //体温
                                    val tempOrgin = decimalOnePlace(
                                        ParseZigUtil.hexToInt(
                                            dataCheck?.substring(
                                                dataCheck.length - 4,
                                                dataCheck.length
                                            )
                                        ) / 100.0
                                    )
                                    activity?.tv_show?.text = (30 + tempOrgin).toString()
                                    Timber.i("数据已存储 ${(30 + tempOrgin)}  ℃")

                                }
                                "66" -> { //血压实时
                                    activity?.tv_show?.text = getCurrentPress(dataCheck).toString()
                                }
                                "67" -> {
                                    val deBean = ParseZigUtil.getPCpress(dataCheck)
                                    activity?.tv_show?.text = "高压 ${deBean.pressureHighValue} mmHg  ,低压 ${deBean.pressureLowValue} mmHg, 脉搏${deBean.jumpValue}  "
                                }
                                "226" -> {
                                    activity?.tv_show?.text = dataCheck?.substring(dataCheck.length - 4, dataCheck.length)?.toInt()?.div(10.0).toString() + "mmol/L"
                                }
                            }
                        }
                    }
//                    Timber.i("界面收到数据  ${zigBean.data.toString()}")
                }
            }
        }
    }


    private var exec: ExecutorService? = null
    var isSpinnerFirst = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jyso)
        DevicePort.Init()
        DevicePort.OpenChannel(CHANNEL, BDR, TYPE)

        exec = Executors.newCachedThreadPool()
        exec?.execute {
            while (true) {
                val ssCha = DevicePort.ReadString(CHANNEL)
                if (ssCha != null && ssCha.isNotEmpty() && "NULL" != ssCha) {
                    Timber.i("读取数据 $ssCha")
                    val message = Message()
                    message.obj = ssCha
                    handler.sendMessage(message)
                }
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        bt_wf_allow.setOnClickListener {
            Timber.i("允许加网  " + DevicePort.WriteString(ALLOW_JOIN))
        }


        val portArray = resources.getStringArray(R.array.port_adapter)

        sp_port.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isSpinnerFirst) {
                    //                    val adapterZig = SharedPrefsUtils.getZig(brand_key);
                    //                    val deviceData = DeviceData(portArray[position], "114", "115200")
                    //                    Timber.i("选择适配器端口 ${portArray[position]}")
                    //                    if (adapterZig != null) {
                    //                        val zigBean = ZigBean(0, adapterZig.short_addr, deviceData, "228", "SET", "")
                    //                        var adapterJson = gson.toJson(zigBean)
                    //                        Timber.i("数据写入适配器  $adapterJson")
                    //                        DevicePort.WriteString(adapterJson)
                    //                    }
                }
                isSpinnerFirst = true
            }
        }


        bt_stop_join.setOnClickListener {
            Timber.i("禁止入网   " + DevicePort.WriteString(DISABLE_JOIN))
        }


        bt_reset_mac.setOnClickListener {
            Timber.i("复位网关" + DevicePort.WriteString(RESET))
        }

    }

    fun initSo(view: View) {
        Timber.i("  initSo     " + DevicePort.Init())
    }

    fun openChannel(view: View) {
        Timber.i("  openChannel     " + DevicePort.OpenChannel(CHANNEL, BDR, TYPE))
    }

    fun CloseChannel(view: View) {
        DevicePort.CloseChannel(CHANNEL)
        if (exec != null) {
            exec?.shutdown()
        }
    }

    fun WriteString(view: View) {
        Timber.i("  WriteString     " + DevicePort.WriteString(ALLOW_JOIN))
    }

    fun ReadString(view: View) {
    }

    private val CHANNEL = 0
    private val BDR = 115200
    private val TYPE = 0xf0


    override fun onDestroy() {
        super.onDestroy()
        DevicePort.CloseChannel(CHANNEL)
        exec?.shutdown()
    }
}

