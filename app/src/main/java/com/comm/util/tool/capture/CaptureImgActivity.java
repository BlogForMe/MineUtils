package com.comm.util.tool.capture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import timber.log.Timber;

/**
 * 截取图片的指定区域
 */
public class CaptureImgActivity extends AppCompatActivity {

    private ImageView ivImgShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_img);

        ivImgShow = findViewById(R.id.iv_img_show);
        findViewById(R.id.bt_capture).setOnClickListener(v -> {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_1585823288110);
            Timber.i(" w "+bitmap.getWidth() + " height "+ bitmap.getHeight());
            Bitmap newBitmap = Bitmap.createBitmap(bitmap, 11, 58, 225,120);

            ivImgShow.setImageBitmap(newBitmap);
        });
    }
}
