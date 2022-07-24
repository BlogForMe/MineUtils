package com.comm.util.component.activityresult;

import java.util.List;

import android.app.ActivityManager;
import android.content.Intent;
import android.view.View;
import com.comm.util.R;
import com.comm.util.base.BaseActivity;
import timber.log.Timber;


//@Route(path = ACTIVITY_SECOND)
public class SecondResultActivity extends BaseActivity {

    public static String sData = "data";


    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(21, new Intent().putExtra(sData, "我要回调啦!!!"));
                finish();
            }
        });

//        findViewById(R.id.bt_islive).setOnClickListener(v -> {
//            boolean isSendo = DisplayUtils.isMainActivityAlive(SecondActivity.this, "FirstResultActivity");
//            boolean isSendo = isExsitActivity(this, FirstResultActivity.class);
//
//            boolean isSecond = isExsitActivity(this, SecondActivity.class);
//
//            boolean isTop = isTopActivity("SecondActivity");
//            Timber.i("isSendo   " + isSendo + "    isSecond " + isSecond + " isTop " + isTop);
//            Timber.i("getLocalClassName   " + this.getsim);
//            startActivity(new Intent(this, ThirdActivity.class));
//        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_second;
    }


    /**
     * 检测某Activity是否在当前Task的栈顶
     */
    private boolean isTopActivity(String activityName) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;
        if (runningTaskInfos != null) {
            cmpNameTemp = runningTaskInfos.get(0).topActivity.getClassName();
            Timber.tag("SecondResultActivity").i("cmpNameTemp   " + cmpNameTemp);
        }
        if (cmpNameTemp == null) {
            return false;
        }
        return cmpNameTemp.equals(activityName);
    }
}
