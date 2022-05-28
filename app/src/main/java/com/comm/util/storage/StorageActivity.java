package com.comm.util.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.comm.util.R;
import com.comm.util.utils.StorageUtil;
import timber.log.Timber;

public class StorageActivity extends AppCompatActivity {

    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 001;
    int number;
    String fileInnerName = "fileInnerName";
    private String rootFile;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        rootFile = StorageUtil.getRootDirectory() + "/zhuji.apk";
        progressBar = findViewById(R.id.progress);

        findViewById(R.id.bt_privatefile_crate).setOnClickListener(v -> {
            createPrivateFile();
        });
    }

    private void createPrivateFile() {
        File fileCache = new File(getCacheDir(), fileInnerName);
        boolean bf = fileCache.mkdirs();
        Timber.i("createPrivateFile " + bf);

    }

    private void checkPermission() {
        // Here, thisActivity is the current activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                            当某条权限之前已经请求过，并且用户已经拒绝了该权限时，shouldShowRequestPermissionRationale ()方法返回的是true
                } else {
//                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                }
            }
        } else {
            createExternalStorage();
        }

    }

    public void btPermission(View v) {
        checkPermission();
    }

    public void btCreateFile(View v) {
        createExternalStorage();
    }

    /**
     * 生成时间戳文件
     *
     * @param view
     */
    public void btFile(View view) {
        try {
            createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
    }

    private void createExternalStorage() {

        String dir = StorageUtil.getCacheDirectory();
        String apkDIR = "apkdir.apk";
        File apkFile = new File(dir, apkDIR);


        if (!StorageUtil.isExternalStorageWritable()) {
            return;
        }
        if (apkFile.exists()) {  //如果存在则删除文件
            apkFile.delete();
        }
        boolean isCreate = false;
        try {
            isCreate = apkFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isCreate) {
            readApk(rootFile, apkFile.getPath());
        }

    }

    private void readApk(String rootFile, String apkFile) {
        byte[] buff = new byte[1024];
        try {
            FileInputStream is = new FileInputStream(rootFile);
            FileOutputStream fos = new FileOutputStream(apkFile);
            int len;
            int sum = 0;
            while ((len = is.read(buff)) != -1) {
                fos.write(buff, 0, len);
                sum += len;
                int progress = (int) (sum * 1.0f / sum * 100);


                progressBar.setProgress(progress);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private File createImageFile() throws IOException {
        number++;
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_" + number;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
