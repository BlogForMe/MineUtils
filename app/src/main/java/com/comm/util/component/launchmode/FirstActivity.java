package com.comm.util.component.launchmode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import com.comm.util.R;
import com.comm.util.base.BaseActivity;
import timber.log.Timber;

import static com.comm.util.component.launchmode.SerachActivity.RESULT_TAG_SEARCH_ROOM;
import static com.comm.util.component.launchmode.SerachActivity.txtSearch;

public class FirstActivity extends BaseActivity {
    public static final String newIntent = "newIntent";
    final static int COUNTS = 5;//点击次数
    final static long DURATION = 3 * 1000;//规定有效时间
    long[] mHits = new long[COUNTS];

    @Override
    protected int setLayoutId() {
        return R.layout.activity_first;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String getSaveDd = savedInstanceState.getString("savedata", "");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("savedata", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String getSaveDd = savedInstanceState.getString("savedata", "");
        Timber.i("getSaveDd " + getSaveDd);
    }

    @Override
    protected void initView() {

        findViewById(R.id.bt_dialog).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
        });

        findViewById(R.id.bt_launch).setOnClickListener(v -> {
            //                Intent intent = new Intent(FirstActivity.this, SerachActivity.class);
            Intent intent = new Intent(this, SecondActivity.class);
            //            startActivityForResult(intent, REQUEST_TAG_SEARCH_ROOM);
            //            recreate();
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            startActivity(intent);

        });

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String data1 = intent.getStringExtra(newIntent);
        String data2 = getIntent().getStringExtra(newIntent);
        setIntent(intent);
        String data3 = getIntent().getStringExtra(newIntent);
        Timber.d("onNewIntent  " + data3);
    }

    //    @OnClick(R.id.bt_launch)
    //    public void btLaunch() {
    //        Intent intent = new Intent(this, LaunchActivity.class);
    //        startActivity(intent);
    //    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_TAG_SEARCH_ROOM == resultCode) {
            String txt = data.getStringExtra(txtSearch);
            Timber.d(txt);
        }
    }
}
