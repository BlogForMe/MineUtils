//package com.jonzhou.mineutils.openlib.fastble;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.clj.fastble.BleManager;
//import com.jonzhou.mineutils.R;
//
//public class FastbleActivity extends AppCompatActivity {
//
//    private static final int WRITE_EXTERNAL_LOCATION_REQUEST_CODE = 22;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fastble);
//        BleManager.getInstance().init(getApplication());
//        BleManager.getInstance()
//                .enableLog(true)
//                .setReConnectCount(1, 5000)
//                .setSplitWriteNum(20)
//                .setConnectOverTime(10000)
//                .setOperateTimeout(5000);
//
//
//        findViewById(R.id.bt_scan).setOnClickListener(v -> {
//            checkPermm();
//        });
//
//    }
//
//    private void checkPermm() {
//        // Here, thisActivity is the current activity
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // Should we show an explanation?
////                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
////                            当某条权限之前已经请求过，并且用户已经拒绝了该权限时，shouldShowRequestPermissionRationale ()方法返回的是true
//                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, WRITE_EXTERNAL_LOCATION_REQUEST_CODE);
////                }
//            } else {
//                startScan();
//            }
//        } else {
//            startScan();
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case WRITE_EXTERNAL_LOCATION_REQUEST_CODE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                    startScan();
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//                // other 'case' lines to check for other
//            }
//        }
//    }
//
//    int i = 0;
//
//    private void startScan() {
//        new Thread(
//                () -> {
//                    while (i < 15) {
//                        try {
//                            Thread.sleep(1000);
//                            i++;
//                            System.out.println("读秒  " + i);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        ).start();
//
//    }
//}
