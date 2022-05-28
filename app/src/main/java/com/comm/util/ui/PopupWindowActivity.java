package com.comm.util.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.utils.PopupWindowUtil;

/**
 * https://blog.csdn.net/xfhy_/article/details/78779108
 */
public class PopupWindowActivity extends AppCompatActivity {

    private View showView;
    private TextView tvAnchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poup_window);
        showView = LayoutInflater.from(this).inflate(R.layout.item_app, null);
        tvAnchor = findViewById(R.id.tv_anchor);
    }

    public void btPopup(View view) {
        PopupWindowUtil popupWindow = new PopupWindowUtil.Builder()
                .context(this)
                .view(showView)
                .anchor(tvAnchor)
                .alpha(0.5f)
                .width(ViewGroup.LayoutParams.WRAP_CONTENT)
                .height(LinearLayout.LayoutParams.WRAP_CONTENT)
                .backgroundDrawable(new ColorDrawable())
                .focusable(true)
                .position(PopupWindowUtil.SCREEN_CENTER).build();
        popupWindow.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doc_dymatic, menu);

        return true;
    }
}
