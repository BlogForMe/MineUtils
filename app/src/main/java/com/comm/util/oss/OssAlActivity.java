//package com.jonzhou.mineutils.oss;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Build;
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.alibaba.sdk.android.oss.ClientConfiguration;
//import com.alibaba.sdk.android.oss.ClientException;
//import com.alibaba.sdk.android.oss.OSS;
//import com.alibaba.sdk.android.oss.OSSClient;
//import com.alibaba.sdk.android.oss.ServiceException;
//import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
//import com.alibaba.sdk.android.oss.common.OSSLog;
//import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
//import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
//import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
//import com.alibaba.sdk.android.oss.model.GetObjectRequest;
//import com.alibaba.sdk.android.oss.model.GetObjectResult;
//import com.jonzhou.mineutils.R;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class OssAlActivity extends AppCompatActivity {
//
//    public static String securityToken = "CAIS9gF1q6Ft5B2yfSjIp6XFHY/tiuxswpqdc1byl2cDVLx8qvfujTz2IHFFe3BhAugesfwynmlR7PkalqNvRoRZAFDDbsZ2thvJIe5vJ9ivgde8yJBZor/HcDHhJnyW9cvWZPqDP7G5U/yxalfCuzZuyL/hD1uLVECkNpv74vwOLK5gPG+CYCFBGc1dKyZ7tcYeLgGxD/u2NQPwiWeiZygB+CgE0D4nufzum5PHtUaG0gKqmtV4/dqhfsKWCOB3J4p6XtuP2+h7S7HMyiY46WIRpfYp1f0foW+Y5ozHWgALvE2cVuPJ7t0qIAhmYKw9EqJUAChMbwrjBIYagAF1NY6bmEcGGiMRQrWpF6aEKSCbXHHxfwT+9XY77qNv6Mqlf8iry6jjnV/vGfOxYfLq6z/GVt8sKHYx/n2lDCI+iCvuaSklz2gsPhyfleeDszBld7r0G27Qm1Wp8esCCTmtScXic7Pf+/TYydohnZBxXfvIQacmZ3tB21YhQ1wp5Q==";
//    public static String accessKeySecret = "CzR2nSUBJYAdYyXQHYDzdEKdASsjKfLHxhi2emYBL7nh";
//    public static String accessKeyId = "STS.FfpV5Yg3MuXvqpCsgVX3PE2Eo";
//    public static String bucketName = "yuqiongyao";
//    public static String objectKey = "/idCard/1521169924371_2021340171.jpg";
//    private String endpoint="http://oss-cn-beijing.aliyuncs.com";
//    private ImageView ivImg;
//
//
//
//    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 6;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_oss_al);
//        checkPermm();
//        ivImg = findViewById(R.id.iv_img);
//    }
//
//
//
//    public void btDown(View view) {
//
//// 在移动端建议使用STS方式初始化OSSClient。
//// 更多信息可查看sample 中 sts 使用方式(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
////        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider("<StsToken.AccessKeyId>", "<StsToken.SecretKeyId>", "<StsToken.SecurityToken>");
//        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, securityToken);
////该配置类如果不设置，会有默认配置，具体可看该类
//        ClientConfiguration conf = new ClientConfiguration();
//        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
//        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
//        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
//        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
////开启可以在控制台看到日志，并且会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv  默认不开启
////日志会记录oss操作行为中的请求数据，返回数据，异常信息
////例如requestId,response header等
////android_version：5.1  android版本
////mobile_model：XT1085  android手机型号
////network_state：connected  网络状况
////network_type：WIFI 网络连接类型
////具体的操作行为信息:
////[2017-09-05 16:54:52] - Encounter local execpiton: //java.lang.IllegalArgumentException: The bucket name is invalid.
////A bucket name must:
////1) be comprised of lower-case characters, numbers or dash(-);
////2) start with lower case or numbers;
////3) be between 3-63 characters long.
////------>end of log
//        OSSLog.enableLog();
//        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
//
//// 构造下载文件请求
//        GetObjectRequest get = new GetObjectRequest(bucketName, objectKey);
//        OSSAsyncTask task = oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
//            @Override
//            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
//                // 请求成功
////                Log.d("Content-Length", "" + getResult.getContentLength());
//                InputStream inputStream = result.getObjectContent();
////                byte[] buffer = new byte[2048];
////                int len;
////                try {
////                    while ((len = inputStream.read(buffer)) != -1) {
////                        // 处理下载的数据
////                    }
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//                try {
//                    Bitmap in = autoResizeFromStream(inputStream);
//                    ivImg.setImageBitmap(in);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                // 请求异常
//                if (clientExcepion != null) {
//                    // 本地异常如网络异常等
//                    clientExcepion.printStackTrace();
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
//                }
//            }
//        });
//    }
//
//
//    //根据ImageView大小自动缩放图片
//    public Bitmap autoResizeFromStream(InputStream stream) throws IOException {
//
//        byte[] data;
//        {
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = stream.read(buffer)) != -1) {
//                outStream.write(buffer, 0, len);
//            }
//            outStream.close();
//            data = outStream.toByteArray();
//            stream.close();
//        }
//
//        // First decode with inJustDecodeBounds=true to check dimensions
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeByteArray(data, 0, data.length, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, 400, 400);
//        Log.d("ImageHeight", String.valueOf(options.outHeight));
//        Log.d("ImageWidth", String.valueOf(options.outWidth));
////        Log.d("Height", String.valueOf(imageView.getWidth()));
////        Log.d("Width", String.valueOf(imageView.getWidth()));
//        //options.inSampleSize = 10;
//
//        Log.d("SampleSize", String.valueOf(options.inSampleSize));
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
//    }
//
//
//    //计算图片缩放比例
//    public static int calculateInSampleSize(
//            BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        // Raw height and width of image
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//
//            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
//            // height and width larger than the requested height and width.
//            while ((halfHeight / inSampleSize) > reqHeight
//                    && (halfWidth / inSampleSize) > reqWidth) {
//                inSampleSize *= 2;
//            }
//        }
//
//        return inSampleSize;
//    }
//
//
//    private void checkPermm() {
//        // Here, thisActivity is the current activity
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                // Should we show an explanation?
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
////                            当某条权限之前已经请求过，并且用户已经拒绝了该权限时，shouldShowRequestPermissionRationale ()方法返回的是true
//                } else {
////                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//                // other 'case' lines to check for other
//                // permissions this app might request
//            }
//        }
//    }
//
//}
