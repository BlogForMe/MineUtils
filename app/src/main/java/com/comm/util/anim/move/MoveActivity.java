package com.comm.util.anim.move;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.util.DisplayUtils;
import com.comm.util.R;

public class MoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        TextView textView = findViewById(R.id.tv_move_me);
        findViewById(R.id.bt_move).setOnClickListener(v -> {
            ObjectAnimator.ofFloat(textView, "translationX", 0, 300).setDuration(1000).start();
        });

        TextView tvText = findViewById(R.id.tv_txt);
        tvText.setText("宽   "+ DisplayUtils.getScreenW(this) + "高   "+ DisplayUtils.getScreenH(this));
    }
}
