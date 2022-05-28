package com.comm.util.dialog.window;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class WindowManagerActivity extends AppCompatActivity {

    private WindowManager.LayoutParams wmParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manager);
//        WindowUtils.showPopupWindow(getApplicationContext());
//
//
//        String sfjj = "{\n" +
//                "      'buglyAPPId' : 'd0a87d6457',\n" +
//                "      'district' : '开发',\n" +
//                "      'host' : 'https://dev.casanubeserver.com',\n" +
//                "      'isNeedCardId' : 'false',\n" +
//                "      'isOdm' : 1,\n" +
//                "      'mqHost' : 'dev.casanubeserver.com'\n" +
//                "    }";

      TextView tvMac=findViewById(R.id.tv_mac);
//      tvMac.setText(DeviceUtil.getMacAddr(this));
    }

    public void createWindow() {
        final WindowManager windowMnager = getWindowManager();  //得到WindowManager对象
        wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //只有打电话才会显示在屏幕上方
        wmParams.gravity = Gravity.CENTER;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        ImageView imageView = new ImageView(this);    //设置添加的图片
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                removeWindow();
            }
        });
        windowMnager.addView(imageView, wmParams);
    }
}
