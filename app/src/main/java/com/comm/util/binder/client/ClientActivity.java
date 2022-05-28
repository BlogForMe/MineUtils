package com.comm.util.binder.client;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;
import com.comm.util.binder.common.IPersonManager;
import com.comm.util.binder.common.Person;
import com.comm.util.binder.common.Stub;
import com.comm.util.binder.server.RmoetService;

public class ClientActivity extends AppCompatActivity {


    private IPersonManager iPersonManager;
    private final ServiceConnection connction = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPersonManager = Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPersonManager= null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Intent intent = new Intent(this, RmoetService.class);
        intent.setAction("com.enjoy.binder");
        bindService(intent,connction, Context.BIND_AUTO_CREATE);
    }

    public void button1(View view) {
        try {
            iPersonManager.addPerson(new Person("john",3));
            List<Person> persons = iPersonManager.getPersonList();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void button2(View view) {
        try {
            iPersonManager.setData(new byte[1*1024*1024-4096*2]);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}