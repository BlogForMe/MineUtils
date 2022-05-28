package com.comm.util.dagger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.android.util.ToastUtil;
import com.comm.util.R;

public class DaggerPresenterActivity extends AppCompatActivity {

//    @Inject
//    LoginModel loginModel;


//    @Inject
//    LoginPresenter loginPresenter;

    String packname = "com.android.factorytest";
    String openActivity = "com.android.factorytest.MainMenu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
//        DaggerLoginModelComponet.builder().build().inject(this);

    }

    public void doAThing(View v) {
//        boolean admin = loginModel.checkUserName("adimn");
//        Timber.d("测试" + admin);

        openApp();
    }

    public void btMvpThing(View v) {
        statActivity();
    }

    void openApp() {
        PackageManager packageManager = getPackageManager();
        if (checkPackInfo(packname)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packname);
            startActivity(intent);
        } else {
            ToastUtil.showShort("没有安装");
        }
    }

    void statActivity() {
        Intent intent = new Intent();
        //第一种方式
        ComponentName cn = new ComponentName(packname, openActivity);
        try {
            intent.setComponent(cn);
            //第二种方式
            //intent.setClassName("com.example.fm", "com.example.fm.MainFragmentActivity");
//            intent.putExtra("test", "intent1");
            startActivity(intent);
        } catch (Exception e) {
            //TODO  可以在这里提示用户没有安装应用或找不到指定Activity，或者是做其他的操作
        }
    }


    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

}
