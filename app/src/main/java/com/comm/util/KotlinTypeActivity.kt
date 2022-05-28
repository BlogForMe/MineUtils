package com.comm.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.bean.BaseCount
import com.comm.util.bean.Site
import com.comm.util.utils.GsonUtil.gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_kotlin_type.*
import timber.log.Timber

class KotlinTypeActivity : AppCompatActivity() {

    private lateinit var archivesInfoList: List<Any>
    val json = "{\"meta\":{\"describe\":\"操作成功\",\"statusCode\":\"0\"}}"

    private var myService: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_type)
        println(myService?.length)
        val type = object : TypeToken<BaseCount<List<Site>>>() {}.type
        var response = gson.fromJson<BaseCount<List<Site>>>(json, type)

        bt_wenhao.setOnClickListener {
            //            val ss = null
//            checkNull(ss)
//            archivesInfoList = response.dataList
//            val dataList = response.dataList
//            dataList?.let { it ->
//                println(it[0].sites)
//            }
//
//            val data = response.data
//            data?.let { it ->
//                it[0].sites
//            }
            val nullTxt:String?=null
            Timber.i("? "+   nullTxt?.length)

            nullTxt?.let {
                Timber.i("let "+   it.length)
            }


        }

        bt_gantan.setOnClickListener {
            val ss = null
            ganTan(ss)
        }
    }

    private fun ganTan(ss: String?) {
        ss!!.length
    }

    private fun checkNull(sdata: String?) {
//        if (sdata != null) {
        val vs = sdata?.length
        println(vs)
//        }
    }
}
