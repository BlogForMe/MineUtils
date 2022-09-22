package com.comm.util;

import java.lang.reflect.Field;
import java.util.Map;

import com.alibaba.android.arouter.launcher.ARouter;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.component.launchmode.ActivityA;
import com.comm.util.utils.JsonParser;

/**
 * @author Jon
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initBox();
//        parseData();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Field field = null;
        try {
            field = wifiManager.getClass().getDeclaredField("WIFI_SCAN_AVAILABLE");
            Log.d("ThirdActivity", (String) field.get(wifiManager));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }





    private void initBox() {
        CheckBox c1 = findViewById(R.id.cb_1);
        CheckBox c2 = findViewById(R.id.cb_2);

        //注册事件监听
        c1.setOnCheckedChangeListener(new CheckBoxListener());
        c2.setOnCheckedChangeListener(new CheckBoxListener());
    }


    public void btPackage(View v) {
//        getAppList();
        ARouter.getInstance().build("/app/BeaconActivity").navigation();
    }

    public void bt_lanchmode(View view) {
        startActivity(new Intent(this, ActivityA.class));
    }

    private void parseData(){
        String  ss ="{\"0\":[" +
                "     {\"id\":1,\"action\":\"1\",\"name\":\"联系医生\"}," +
                " {\"id\":2,\"action\":\"2,8\",\"name\":\"收听消息\"}," +
                "     {\"id\":3,\"action\":\"3\",\"name\":\"换人使用\"}," +
                "     {\"id\":4,\"action\":\"4\",\"name\":\"查看帮助\"}," +
                "     {\"id\":5,\"action\":\"5\",\"name\":\"切换模式\"}" +
                "  ], \"1\":[" +
                "{\"id\":1,\"action\":\"1\",\"name\":\"联系医生\"}," +
                "{\"id\":2,\"action\":\"6\",\"name\":\"体征测量\"}," +
                "{\"id\":3,\"action\":\"2,8\",\"name\":\"收听消息\"}," +
                "{\"id\":4,\"action\":\"3\",\"name\":\"换人使用\"}," +
                "{\"id\":5,\"action\":\"5\",\"name\":\"切换模式\"}" +
                "  ], \"2\":[" +
                "{\"id\":1,\"action\":\"1\",\"name\":\"联系医生\"}," +
                "{\"id\":2,\"action\":\"6\",\"name\":\"体征测量\"}," +
                "{\"id\":3,\"action\":\"2,8\",\"name\":\"收听消息\"}," +
                "{\"id\":4,\"action\":\"3\",\"name\":\"换人使用\"}," +
                "{\"id\":5,\"action\":\"5\",\"name\":\"切换模式\"} ] }";

//            JSONObject jsonObject = new JSONObject(ss);
            Map jsonObject = JsonParser.decode(ss);
            System.out.println(jsonObject);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取非系统应用信息列表
     */


    class CheckBoxListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            if (isChecked) {
                //Toast
                Toast.makeText(MainActivity.this, buttonView.getText() + "被选择", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, buttonView.getText() + "取消选择", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
