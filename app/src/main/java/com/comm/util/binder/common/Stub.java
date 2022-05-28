package com.comm.util.binder.common;

import java.util.List;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Stub extends Binder implements  IPersonManager {
    public Stub() {
        this.attachInterface(this,DESCRIPTOR);
    }

    public static  IPersonManager asInterface(IBinder binder){
        if (binder==null){
            return  null;
        }
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if ((iin!=null)&&(iin instanceof  IPersonManager)){
            return (IPersonManager) iin;
        }
        return new Proxy(binder);
    }

    @Override
    public void addPerson(Person person) throws RemoteException {

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
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
//            case TRANSACTION_addPerson:
//                data.enforceInterface(DESCRIPTOR);
//                Person arg0 = null;
//                if ((0!=data.readInt())){
//
//                }

        }
        return super.onTransact(code, data, reply, flags);
    }
}
