package com.comm.util.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.comm.util.R;


public class PermissionActivity extends AppCompatActivity {
    public final static String TAG = "PermissionActivity";
    public static final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.bt_grant).setOnClickListener(v -> {
            requestPermission();
        });
    }

    void requestPermission() {
        // Here, thisActivity is the current activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //则每次执行需要这一权限的操作时您都必须检查自己是否具有该权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                            当某条权限之前已经请求过，并且用户已经拒绝了该权限时
                    //异步向用户显示解释，这里先弹出解释框，再请求权限.
                    Log.d(TAG, "用户看到解释后，再次常识请求权限");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    //无需解释，首次请求许可
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    Log.d(TAG, "requestPermissions");
                }
            } else {
                //权限已经授予
                Log.d(TAG, "requestPermissions is ok");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty. 如果取消请求，则grantResult 为空
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionResult:ok");
                } else {
                    Log.d(TAG, "onRequestPermissionResult:no");
                }
                return;
            }
        }
    }


}
