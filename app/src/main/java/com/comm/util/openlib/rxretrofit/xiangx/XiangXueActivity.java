package com.comm.util.openlib.rxretrofit.xiangx;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.utils.Constant;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * RxJava 思想
 */
public class XiangXueActivity extends AppCompatActivity {

    private final String IMG_PATH = "";
    private ImageView iv_rx_show;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_xue);
        iv_rx_show = findViewById(R.id.iv_rx_show);
    }

    public void rxJavaDownloadImageAction(View view) {
        Observable.just(Constant.imageUrl1)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        Timber.d(s);
                        URL url = new URL(s);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(10000);
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            return bitmap;
                        }
                        return null;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        Paint paint = new Paint();
                        paint.setTextSize(88);
                        paint.setColor(Color.RED);
                        return drawTextToBitmap(bitmap,"大家好吗",paint,88,88);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        progressDialog = new ProgressDialog(XiangXueActivity.this);
                        progressDialog.setTitle("RxJava John");
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        iv_rx_show.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        progressDialog.hide();
                    }
                });

    }


    // 图片上绘制文字 加水印
    private final Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true);//获取更清晰的图像采用
        paint.setFilterBitmap(true); //过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }


}