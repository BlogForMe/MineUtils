package com.comm.util.ui.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

//https://my.oschina.net/ososchina/blog/3018393
public class TextSpanActivity extends AppCompatActivity {

    String sdf = "\"<p><a href=\"https://img.alicdn.com/imgextra/i1/3216525108/TB2Nz9CXsPRfKJjSZFOXXbKEVXa_!!3216525108.jpg\"><img alt=\"\" src=\"https://img.alicdn.com/imgextra/i1/3216525108/TB2Nz9CXsPRfKJjSZFOXXbKEVXa_!!3216525108.jpg\" style=\"height:620px; width:1920px\" /></a></p>\n" +
            "\"";

    private TextView tvShow;


    //    @BindView(R.id.et_l1)
//    EditTextRight etTextRight;
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

//        String source = "<html>今天<span style='color:#FFE31335;font-size:16sp;background-color:white;'>星期三</span>，<span style='color:#fff;font-size:14sp;background-color:red;'>但是我还要加班</span><html>";

//        tvShow = findViewById(R.id.tv_show);
//        tvShow.setText(Html.fromHtml(source, new MyImageGetter(this, tvShow), this));

//        tvShow = findViewById(R.id.tv_show);
//        tvShow.setHtml(sdf, new HtmlHttpImageGetter(tvShow));


        /**
         * 把Html转化成原生的span标签
         */
//        HtmlTagHandler htmlTagHandler = new HtmlTagHandler();
//        htmlTagHandler.registerTag("span",new SpanTag(this));
//        final Spanned spanned = Html.fromHtml(source, htmlTagHandler, htmlTagHandler);
//        tvShow.setText(spanned );

        TextView tvSpan = findViewById(R.id.tv_span);
        String agreeTxt = "欢迎使用家云健康! 家云极为重视您的个人隐私，并尊重您对个人隐私各种权利，我们将严格在国家法律允许范围内，且只以为您提供服务为目的，收集必要的相关信息，我们将尽力保障这些信息的安全，你可以阅读<<用户协议>>和 <<隐私政策>>了解详细信息。 如您同意，请点击同意开始接受我们的服务。";
        SpannableString spanTxt = new SpannableString(agreeTxt);
        spanTxt.setSpan(new ForegroundColorSpan(Color.GREEN), 20, 30, spanTxt.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpan.setText(spanTxt.toString());

    }
//
//    @OnClick(R.id.bt_input)
//    public void btInput() {
//        String txt = etTextRight.getText().toString();
////        ToastUtil.showToast(this, txt);
//        Log.i("TextViewActivity","" +txt);
//    }

}
