/*
package com.comm.util.openlib.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.comm.util.R;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import timber.log.Timber;

public class QRCodeActivity extends AppCompatActivity {
    public final int CUSTOMIZED_REQUEST_CODE = 0x0000ffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    public void scanBarcode(View view) {
        new IntentIntegrator(this).setRequestCode(CUSTOMIZED_REQUEST_CODE).initiateScan();
    }

    public void scanCustomScanner(View view) {
        new IntentIntegrator(this).setRequestCode(CUSTOMIZED_REQUEST_CODE).setOrientationLocked(false).setCaptureActivity(CustomScannerActivity.class).initiateScan();
    }


    // Get the results:
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
//            // This is important, otherwise the result will not be passed to the fragment
//            super.onActivityResult(requestCode, resultCode, data);
//            return;
//        }
//        switch (requestCode) {
//            case CUSTOMIZED_REQUEST_CODE: {
//                Toast.makeText(this, "REQUEST_CODE = " + requestCode, Toast.LENGTH_LONG).show();
//
//                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//                if (result != null) {
//                    if (result.getContents() == null) {
//                        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//
//                        Timber.i("onActivityResult  " + result.getContents());
//                    }
//                }
//                break;
//            }
//            default:
//                break;
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//            }
//
//            Timber.i("onActivityResult  " + result.getContents());
//
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case CUSTOMIZED_REQUEST_CODE: {
                Toast.makeText(this, "REQUEST_CODE = " + requestCode, Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;
        }

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if(result.getContents() == null) {
            Intent originalIntent = result.getOriginalIntent(); //
            if (originalIntent == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Log.d("MainActivity", "Cancelled scan due to missing camera permission");
                Toast.makeText(this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d("MainActivity onActivityResult", "Scanned");
            Toast.makeText(this, "Scanned:  onActivityResult " + result.getContents(), Toast.LENGTH_LONG).show();
        }
    }


}
*/
