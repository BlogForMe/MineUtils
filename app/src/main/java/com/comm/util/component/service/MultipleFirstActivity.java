package com.comm.util.component.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.comm.util.R;
import com.comm.util.openlib.rxretrofit.renyugang.Person;

public class MultipleFirstActivity extends Activity implements ContentService.Callback {

    private MyServiceConn conn;
    private ContentService service;
    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutiple_first);

        conn=new MyServiceConn();
        bindService(new Intent(this, ContentService.class), conn,
                BIND_AUTO_CREATE);

        mContent = findViewById(R.id.content);

        this.findViewById(R.id.button1).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        startActivity(new Intent(MultipleFirstActivity.this,
                                OtherActivity.class));
                    }
                });
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

    public class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ((ContentService.LocalBinder) binder).getService();
            //将当前activity添加到接口集合中
            service.addCallback(MultipleFirstActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            service = null;
        }
    }
}
