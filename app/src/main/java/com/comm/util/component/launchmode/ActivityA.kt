package com.comm.util.component.launchmode

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.comm.util.base.BaseActivity
import com.comm.util.databinding.ActivityFirstBinding
import timber.log.Timber

class ActivityA : BaseActivity() {

    val binding by lazy { ActivityFirstBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val getSaveDd = savedInstanceState.getString("savedata", "")
        }
        setContentView(binding.root)
        binding.btActivtyA.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("savedata", "save")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val getSaveDd = savedInstanceState.getString("savedata", "")
        Timber.i("getSaveDd $getSaveDd")
    }

    override fun initView() {
//        findViewById<View>(R.id.bt_dialog).setOnClickListener { v: View? ->
//            AlertDialog.Builder(this)
//                .setPositiveButton("确认") { dialog, which -> }
//                .setNegativeButton("取消") { dialog, which -> }.create().show()
//        }
//        findViewById<View>(R.id.bt_launch).setOnClickListener { v: View? ->
//            //                Intent intent = new Intent(FirstActivity.this, SerachActivity.class);
//            val intent = Intent(this, ActivityB::class.java)
//            //            startActivityForResult(intent, REQUEST_TAG_SEARCH_ROOM);
//            //            recreate();
//            //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent)
//            startActivity(intent)
//        }

        //        FloatingActionButton fabButton =findViewById(R.id.fab_button);
        //        findViewById(R.id.fab_button).setOnClickListener(v->{

        //            fabButton.show();
        //            fabButton.hide();

        //
        //                /**
        //                 * 实现双击方法
        //                 * src 拷贝的源数组
        //                 * srcPos 从源数组的那个位置开始拷贝.
        //                 * dst 目标数组
        //                 * dstPos 从目标数组的那个位子开始写数据
        //                 * length 拷贝的元素的个数
        //                 */
        //                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //                //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续5次点击
        //                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
        //                    String tips = "您已在[" + DURATION + "]ms内连续点击【" + mHits.length +
        //                    "】次了！！！";
        //                    Toast.makeText(FirstActivity.this, tips, Toast.LENGTH_SHORT).show();
        //                }
        //            String serialNum = android.os.Build.SERIAL;
        //            ToastUtils.showShortToast("serialNum " + serialNum);

        //            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        //            Timber.i("hour  "+ hour);
        //            SimpleDateFormat sdf = new SimpleDateFormat(HHCmm);
        //            Date startD = null;
        //            try {
        //                startD = sdf.parse("11");
        //                Date endD = sdf.parse("5");
        //                Timber.i( "startD    " + startD.getTime() + "    endD " + endD.getTime());
        //            } catch (ParseException e) {
        //                e.printStackTrace();
        //            }

        //        });
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val data1 = intent.getStringExtra(newIntent)
        val data2 = getIntent().getStringExtra(newIntent)
        setIntent(intent)
        val data3 = getIntent().getStringExtra(newIntent)
        Timber.d("onNewIntent  $data3")
    }

    //    @OnClick(R.id.bt_launch)
    //    public void btLaunch() {
    //        Intent intent = new Intent(this, LaunchActivity.class);
    //        startActivity(intent);
    //    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (SerachActivity.RESULT_TAG_SEARCH_ROOM == resultCode) {
            val txt = data!!.getStringExtra(SerachActivity.txtSearch)
            Timber.d(txt)
        }
    }

    companion object {
        const val newIntent = "newIntent"
        const val COUNTS = 5 //点击次数
        const val DURATION = (3 * 1000 //规定有效时间
                ).toLong()
    }
}