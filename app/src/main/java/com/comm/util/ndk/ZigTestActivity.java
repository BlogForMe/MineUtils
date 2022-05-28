package com.comm.util.ndk;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.DevicePort;
import com.comm.util.R;
import timber.log.Timber;

public class ZigTestActivity extends AppCompatActivity {

    private final static int CHANNEL = 0;
    private final static int BDR = 115200;
    private final static int TYPE = 0xf0;
    private final String write = "{\n" +
            "    \"cmd\":\"ALLOW_JOIN\",\n" +
            "    \"model\":\"240\",\n" +
            "    \"sid\":\"\",\n" +
            "    \"short addr\":0,\n" +
            "    \"endpoint\":0,\n" +
            "    \"data\":null\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zig_test);
    }

    public void initSo(View view) {
        Timber.i("  initSo     " + DevicePort.Init());
    }

    public void openChannel(View view) {
        Timber.i("  openChannel     " + DevicePort.OpenChannel(CHANNEL, BDR, TYPE));
    }

    public void CloseChannel(View view) {
        DevicePort.CloseChannel(CHANNEL);
    }

    public void WriteString(View view) {
        Timber.i("  WriteString     " + DevicePort.WriteString(write));
    }

    public void ReadString(View view) {
        new Thread(() -> {
            while (true) {
                String ssCha = DevicePort.ReadString(CHANNEL);
                Timber.i("ReadString  " + ssCha);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public  void destory(View view){
        DevicePort.Destroy();
    }
}
