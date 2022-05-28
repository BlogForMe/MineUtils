package com.comm.util.anim.textview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

import static com.comm.util.utils.SecurityUtils.AESDecodeAllPlatform;
import static com.comm.util.utils.SecurityUtils.PASSWORD_CONFIG_KEY;


public class TextViewActivity extends AppCompatActivity {

    private TextView tvNext;
    private ImageView ivGif;
    private View myView2;
    private TextView tvCommit;
    private String strEn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view2);

//        tvNext = findViewById(R.id.tv_next);
//        ivGif = findViewById(R.id.iv_gif);
//        myView2 = findViewById(R.id.my_view2);
//        tvCommit = findViewById(R.id.tv_commit);


        findViewById(R.id.bt_start).setOnClickListener(v -> {
//            String dfa  = "rIrzbwa3JJHHfbm723cLtQtY4CzZ9Hqp9chqgRo/Vgebv7sxLZDdK1kTg3gz3A7nAtN4cYunfB2IQIXZHiD2YbukAIQF5cxKIOCsyK6tq9rLi8smJm4YgGyjuZPY//XJPDr0Oy+H6AIkLb/9r9hgAvj0R7CyZXefcQOVdVAiIrh9MPSHUqN6VHiA1k64XLeteqcXTPR/XF6g4CS5N+pY/rwSMIH4zSbcN7q/WsyyVL8ovmGejs3h1Q7vvN+2Kqyr6wfZvB6/BZPbLToErMlRXALO4Feq5YgvIJvpPc2fgmE=";


//            String content = "FAJFLJLEJEJEJ";
//            String pStr = SecurityUtilsNew.AESEncode(keyBytes,content);
//            Timber.i("加密前："+content);
//            Timber.i("加密后:" + pStr);
//
//            String postStr =SecurityUtilsNew.AESDecode(keyBytes,pStr);
//            Timber.i("解密后："+ postStr );

//            String content = "FAJFLJLEJEJEJ";
//            String pStr = SecurityUtils.AESEncode(keyBytes,content);
//            Timber.i("加密前："+content);
//            Timber.i("加密后:" + pStr);
//
//            String postStr =SecurityUtils.AESDecode(keyBytes,pStr);
//            Timber.i("解密后："+ postStr );
        });


        findViewById(R.id.bt_stop).setOnClickListener(v -> {
//            AES.test1();
          String sffNew ="ugyetMkRIHwHrjeoE169dB3LIji5gWPUmmsRfHERkgo4Ht+M4mBjTdl4Vy/Uq+m9Ko20Pe4kZIYpCi0Wa0pBqpXqXxsUidEnIvd8o+m07wmkqy1TMV3Cl01UDQMTkKhWJJUJnUFJYiQwGrjeYVQsFcoBZ28nS2ovqSILGGKPV/eOCfpNm58B0wWPXljF4zZ9Q+mL2w3WnUhIF0UuGHrl7CAcHczIMsIqP+wOk1vcdkbLOEBu7Z/T7iIhyhNt3Flh3vSCGvRJq28mguN4pWR29ADDL+F8HMUqJW99bjolOck=";

//            String str = AESEncodeAllPlatform("kfafafafpppppppp", "gggggggggggggggggggggggggggggggggggggggggg");
            String content = AESDecodeAllPlatform(PASSWORD_CONFIG_KEY, sffNew);

            try {
                JSONObject jsonObject = new JSONObject(content);
                String buglyAPPId = jsonObject.getString("buglyAPPId");
                String district = jsonObject.getString("district");
                String  host=jsonObject.getString("host");
                String isNeedCardId = jsonObject.getString("isNeedCardId");
                int isOdm = jsonObject.getInt("isOdm");
                String mqHost = jsonObject.getString("mqHost");

            Timber.i("  buglyAPPId       "+buglyAPPId + " district "+district + "  host " +host
                     +"isNeedCardId "+isNeedCardId+" isOdm	" + isOdm+"	mqHost "+mqHost);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }

//    boolean flag;
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (flag) {
//                Timber.i(msg.what + "   颜色切换0");
//                tvNext.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                flag = false;
//            } else {
//                Timber.i(msg.what + "   颜色切换1");
//                tvNext.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//                flag = true;
//            }
//        }
//    };


//    private void startThread() {
//        CountDownTimer mTimer = new CountDownTimer(10000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                Timber.i("millisUntilFinished   " + millisUntilFinished);
//                if (millisUntilFinished / 1000 % 2 == 0) {
//                    Timber.i("   颜色切换0");
//                    tvNext.setBackgroundResource(R.drawable.bg_btn_rect_change);
//                } else {
//                    Timber.i("   颜色切换1");
//                    tvNext.setBackgroundResource(R.drawable.bg_btn_rect_check);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        mTimer.start();
//    }


//    public void start() {
//        Timber.i("开始闪烁");
//        FlashHelper.getInstance().startFlick(tvNext);
////        Glide.with(this).load(R.drawable.img_bloodoxy).into(ivGif);
////        ObjectAnimator anim = ObjectAnimator.ofObject(myView2, "color", new ColorEvaluator(),
////                "#0000FF", "#FF0000");
////        anim.setDuration(8000);
////        anim.start();
//    }

//    public void stop() {
//        Timber.i("停止闪烁 ");
//        FlashHelper.getInstance().stopFlick(tvNext);
//    }

}
