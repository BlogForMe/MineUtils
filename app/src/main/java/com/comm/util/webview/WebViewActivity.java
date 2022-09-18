package com.comm.util.webview;

import java.util.Collection;
import java.util.LinkedList;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.utils.NetworkUtil;
import timber.log.Timber;

import static android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK;


/**
 *
 * 用 https://blog.csdn.net/android_freshman/article/details/79461671设置setDomStorageEnabled存储解决问题
 */
public class WebViewActivity extends AppCompatActivity {
//    private static final String API_TEST = "http://116.62.149.166:8601";
    public final static String APP_CACHE_DIR="/APP_CACHE_DIR/";
    public final static String APP_CACHE_DB="/APP_CACHE_DB/";
    String baseUrl = "https://dev.casanubeserver.com/mobileMeasure/?operateWay=0&sessionId=270d8e5507d3e4a8e74697a286b92c9a1fc2de5a70e7a68b91227acae299eba6ecd5e354341741aa0f013a6a5b2d4d48";
    String paramUrl = "&patientCode=11426#/TemperatureList";
    Collection<String> urlCollection;
    boolean isRedirect = false; //是否是重定向
    boolean isPageOk = false; //目的地地址加载完成
//    String loadUrl = "https://dev.casanubeserver.com/mobileMeasure/?operateWay=0&sessionId=ae2b1c87edfbf258d0460169a36a75a1d4a367883386dc62c3114680c4a0a2ef2317c17db3a3635c9f62c232d02fc51b&patientCode=12050#/PressureList/12050";
//    String loadUrl = "https://m.sogou.com/";
    private WebView mWebView;
    private String latestUrl;
    private String prevUrl;
    private WebSettings webSettings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        urlCollection = new LinkedList<>();
        initWebView();
        initData();
    }

    private void initWebView() {
        mWebView = findViewById(R.id.wb_view);
         webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 启用支持javascript
        webSettings.setCacheMode(LOAD_CACHE_ELSE_NETWORK);//缓存模式
        webSettings.setDomStorageEnabled(true); // 开启DOM storage API 功能
//         开启database storage API功能
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);//可以访问文件
        webSettings.setBuiltInZoomControls(true);//支持缩放

        webSettings.setAllowFileAccessFromFileURLs(true);
        //解决图片不显示
        webSettings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIR;
        String cacheDirDB = getCacheDir().getAbsolutePath() + APP_CACHE_DB;
        Timber.i("cacheDirPath  " + cacheDirPath /*+ " cacheDirDB " + cacheDirDB*/);
        //webSettings.setAppCacheEnabled(true);
        //        String databasePath = this.getApplicationContext().getDir("database", Context
        //        .MODE_PRIVATE).getPath();
        //        webSettings.setDatabasePath(cacheDirDB);
        //        webSettings.setAppCachePath(cacheDirPath);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //                Timber.i("shouldOverrideUrlLoading " + url);
                //                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                //                //hitTestResult==null解决重定向问题
                //                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                //                view.loadUrl(url);
//                    return true;
//                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    protected void initData() {

        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
//        }

//        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");   // android点击h5回调

//        String loadUrl = "https://dev.casanubeserver.com/mobileMeasure/?operateWay=0&sessionId=720e4fd89f83040d779a1c5ff8ae11ace08592392645f80e3141ffe83c76a89f0eeb961cb28ac9520678a90dede3a4a7&patientCode=11817#/PressureList/11817";
//        mWebView.loadUrl("file:///android_asset/test.html");

//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                Timber.i("shouldOverrideUrlLoading " + url);
//
////                WebView.HitTestResult hitTestResult = view.getHitTestResult();
////                //hitTestResult==null解决重定向问题
////                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
////                    view.loadUrl(url);
////                    return true;
////                }
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//            }

//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
////                super.onReceivedSslError(view, handler, error);
//                handler.proceed();    //表示等待证书响应
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                Timber.i("onPageStarted " + url);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                Timber.i("onPageFinished " + url);
//            }
//
//
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.e("测试", "====shouldOverrideUrlLoading====");
//                isRedirect = false;
//                if (isPageOk) {
//                    //页面跳转
//                    return true;
//                }
//                return false;//返回true表明点击网页里面的连接还是在当前的webview里跳转,不跳到浏览器
//            }
//        });

        if(NetworkUtil.isOnline()){
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }else {
            webSettings.setCacheMode(LOAD_CACHE_ELSE_NETWORK);
        }
        String loadUrl = baseUrl+paramUrl;
        mWebView.loadUrl(loadUrl);

//
//        mWebView.loadUrl(ss);
//
//
//        String html = "参数";
//        mWebView.loadUrl("javascript:readFromNative('" + html + "')");
    }


//    public void btParam(View view) {
////        String html = "参数";
//        // 传递参数调用JS的方法
////        mWebView.loadUrl("javascript:readFromNative('" + html + "')");
//
//
////       mWebView.loadUrl("javascript:readFromNative('" + html + "');");
//
////        String color = "#00ee00";
////        mWebView.loadUrl("javascript:changeColor('" + color + "');");
//
//        String msg = "差了几分";
//        //调用js中的函数：showInfoFromJava(msg)
//        mWebView.loadUrl("javascript:getParam('" + msg + "')");
//    }


    class HybridInterface {
        Context context;

        public HybridInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void getUserKey(String userKey) {
//            ToastUtil.showToast(context, userKey);
        }
    }


    class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void btClose() {
            Toast.makeText(mContext, "dd", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mWebView.onResume();
//        // ...
//    }
//
//    @Override
//    protected void onPause() {
//        mWebView.onPause();
//        // ...
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        // ...
//        super.onDestroy();
//    }


}