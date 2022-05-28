package com.comm.util.openlib.eventbus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.comm.util.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Jon
 *  https://segmentfault.com/a/1190000004279679
 */
public class EventBusActivity extends AppCompatActivity {

    private TextView btEventRecieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        usePer();
        btEventRecieve= findViewById(R.id.bt_event_recieve);
        EventBus.getDefault().register(this);

        findViewById(R.id.bt_send).setOnClickListener(v->{
            Intent intent = new Intent(this,GoalEventActivity.class);
            startActivity(intent);
//             EventBus.getDefault().post("你好");
//              EventBus.getDefault().post(new MessageEvent("hehe"));
        });
        Switch stWardBt= findViewById(R.id.st_ward_bt);
        stWardBt.setChecked(true);
        stWardBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch aSwitch = (Switch)v;
               aSwitch.setHighlightColor(getResources().getColor(R.color.green));
                if (aSwitch.isChecked()){
                    aSwitch.setTextOn("开");
                }else {
                    aSwitch.setTextOn("关");
                }
            }
        });

        findViewById(R.id.bt_AppInfo).setOnClickListener(v->{
            toSelfSetting();
        });
    }

    public  void toSelfSetting() {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", "com.tencent.mm", null));
        }
        startActivity(mIntent);
    }

    private void usePer(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //则每次执行需要这一权限的操作时您都必须检查自己是否具有该权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                            当某条权限之前已经请求过，并且用户已经拒绝了该权限时，shouldShowRequestPermissionRationale ()方法返回的是true
                } else {
//                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }
            }
        }

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  onMessageEvent(String message){
        btEventRecieve.setText(message);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  onMessageEvent(MessageEvent messageEvent){
        btEventRecieve.setText(messageEvent.getMessage());
        //Log.i("EventBusActivity","  messageEvent.getMessage " + messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
