package com.comm.util.defdata;

public class SetData extends DefaultObserver {
//    @Override
//    public void onSuccess(Object response) {
//        System.out.println("SetData " + response);
//    }


    @Override
    public void onNext(Object response) {
//        super.onNext(response);
        System.out.println("onNext");
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object response) {

    }
}
