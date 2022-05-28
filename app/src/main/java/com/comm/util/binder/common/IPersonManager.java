package com.comm.util.binder.common;

import java.util.List;

import android.os.IInterface;
import android.os.RemoteException;

public interface IPersonManager extends IInterface {
    String DESCRIPTOR = " com.comm.util.binder.common.IPersonManager";

    void addPerson(Person person) throws RemoteException;

    List<Person> getPersonList() throws RemoteException;

    void setData(byte[] data) throws RemoteException;

}
