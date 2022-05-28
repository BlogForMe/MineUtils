package com.comm.util.binder.server;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.comm.util.binder.common.Person;
import com.comm.util.binder.common.Stub;

public class RmoetService  extends Service {
    String TAG = "RmoetService";
    private ArrayList<Person> persons;
    private final IBinder iBinder = new Stub(){
        @Override
        public List<Person> getPersonList() throws RemoteException {
            return persons;
        }

        @Override
        public void setData(byte[] data) throws RemoteException {
            Log.i(TAG, "setData:  数据发送成功");
        }

        @Override
        public IBinder asBinder() {
            return super.asBinder();
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            persons.add(person);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return null;
    }
}
