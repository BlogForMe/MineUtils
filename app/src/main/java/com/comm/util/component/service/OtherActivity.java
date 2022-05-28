package com.comm.util.component.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.comm.util.R;
import com.comm.util.openlib.rxretrofit.renyugang.Person;

public class OtherActivity extends Activity implements ContentService.Callback {

    private ContentService service;
    private TextView mContent;
    private Button mSubmit;
    private EditText mEditText;
    private ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_other);

        mSubmit = findViewById(R.id.button1);
        mContent = findViewById(R.id.content);

        mSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                service.asyncSendPerson("OtherActivity name");
            }
        });


        conn=new MyServiceConn();
        bindService(new Intent(OtherActivity.this, ContentService.class), conn,
                BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unbindService(conn);
    }

    /**
     * 获取回调的内容，更新UI
     */
    @Override
    public void getPerson(Person person) {
        // TODO Auto-generated method stub
        mContent.setText(person.getName());
    }

    public final class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            // TODO Auto-generated method stub
            service = ((ContentService.LocalBinder) binder).getService();
            //将当前activity添加到接口集合中
            service.addCallback(OtherActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            service = null;
        }
    }
}
