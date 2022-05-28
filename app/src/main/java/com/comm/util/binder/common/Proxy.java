package com.comm.util.binder.common;

import java.util.List;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;


public class Proxy implements IPersonManager{
    private final IBinder mRemote;

    public Proxy(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public void addPerson(Person person) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if ((person!=null)){
                data.writeInt(1);
                person.writeToParcel(data,0);
            }else {
                data.writeInt(0);
            }
//        mRemote.transact(Stub.TRANSACTION_add)
            reply.readException();
        }finally {
            reply.recycle();
            data.recycle();
        }

    }

    @Override
    public List<Person> getPersonList() throws RemoteException {
        return null;
    }

    @Override
    public void setData(byte[] data) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
