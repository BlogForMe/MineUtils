package com.comm.util.openlib.glide;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.GlideApp;
import com.comm.util.R;
import com.comm.util.utils.Constant;

public class GlideActivity extends AppCompatActivity {

    private ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        ivImg = findViewById(R.id.iv_img);
        initImg();
    }

    private void initImg() {
//        Glide.with(this).load(Constant.imageUrl).into(ivImg);
        GlideApp.with(this)
                .load(Constant.imageUrl)
                .placeholder(R.mipmap.header_background)
                .fitCenter()
                .into(ivImg);
    }
}
