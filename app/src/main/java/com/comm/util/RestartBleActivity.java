package com.comm.util;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.util.ToastUtil;
import com.android.util.sysdialog.FireMissilesDialogFragment;

public class RestartBleActivity extends AppCompatActivity implements FireMissilesDialogFragment.IConfirmListener {

    public static int REQUEST_ENABLE_BT = 1000;
    Handler mHandler;
    BluetoothAdapter mBluetoothAdapter;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_ble);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        findViewById(R.id.bt_open_ble).setOnClickListener(v -> {
//            mBluetoothAdapter.enable();
            mHandler.sendEmptyMessageDelayed(0, 1500);

        });

        findViewById(R.id.bt_off_ble).setOnClickListener(v -> {
//            Intent enableBtIntent = new Intent(
//                    BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            mBluetoothAdapter.disable();

        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent enableBtIntent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        };
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                            BluetoothAdapter.ERROR);
                    switch (state) {
                        case BluetoothAdapter.STATE_OFF:
                            Toast.makeText(RestartBleActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessageDelayed(0, 1500);
                            break;
                        case BluetoothAdapter.STATE_ON:
                            Toast.makeText(RestartBleActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                            break;
                        case BluetoothAdapter.STATE_TURNING_ON:
                            Toast.makeText(RestartBleActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            Toast.makeText(RestartBleActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        };
        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void negativeBt() {
        ToastUtil.showBiggerText("??????");
    }

    @Override
    public void positiveBt() {
        ToastUtil.showBiggerText("??????");
        mHandler.sendEmptyMessageDelayed(0, 1500);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == -1) {

            } else if (resultCode == 0) {
                FireMissilesDialogFragment.Companion.newInstance().show(getSupportFragmentManager(),"");

            }
        }

    }


    public interface RestartListener {
        void onRestartComplete();
    }


//    private void show(){
//        new CircleDialog.Builder()
//                .configPositive(params -> {
//                    params.textColor = Color.parseColor("#EF8741");
//                    params.textSize = 35;
//                    params.height = 90;
//                })
//                .configNegative(params -> {
//                    params.textColor = Color.parseColor("#ff999999");
//                    params.textSize = 35;
//                    params.height = 90;
//                })
//                .configText(
//                        params -> {
//                            params.textSize = 35;
//                            params.padding =new int[]{20,20,20,20};
//                        }
//                )
//                .setWidth(0.5f)
//                .setText("?????????????????????,?????????????????????")
//                .setNegative("??????",null)
//                .setPositive("??????", null)
//                .setOnShowListener(dialog ->{
////                            mHandler.sendEmptyMessageDelayed(0,1500);
//                    Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
//                })
//                .setOnCancelListener(dialog ->
//                        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show())
//                .show(getSupportFragmentManager());
//    }
}
