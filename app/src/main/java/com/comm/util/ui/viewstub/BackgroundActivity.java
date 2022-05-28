package com.comm.util.ui.viewstub;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;


/**
 * 按钮背景颜色设置　背景变化
 */
public class BackgroundActivity extends AppCompatActivity {


    private ViewStub viewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        viewStub = findViewById(R.id.view_stub);
    }


    public void btBgChange(View v) {
        View iv_vsContent = viewStub.inflate();
        TextView tvText = iv_vsContent.findViewById(R.id.tv_text);
        tvText.setText("你好");
    }


    public void btSetSelect(View v) {
        v.setSelected(!v.isSelected());
    }


}
