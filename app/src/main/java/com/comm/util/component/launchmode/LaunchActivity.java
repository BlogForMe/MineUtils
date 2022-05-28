package com.comm.util.component.launchmode;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.comm.util.R;
import com.comm.util.base.BaseActivity;

/**
 * Activity启动模式
 */
public class LaunchActivity extends BaseActivity {


    private final static String LOG_TAG = "com.comm.util.component.launchmode.in.process";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_lanch;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.bt_hint_intent).setOnClickListener(v->{
            Intent intent = new Intent(LOG_TAG);
            startActivity(intent);
        });
    }

    /**
     * 跳回FirstActivity
     */
//    @OnClick(R.id.bt_back)
//    public void FirstBack() {
//        Intent intent = new Intent(this, FirstActivity.class);
//        intent.putExtra(FirstActivity.newIntent, "6");
//        startActivity(intent);
//    }
}
