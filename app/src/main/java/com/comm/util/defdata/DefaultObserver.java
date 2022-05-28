package com.comm.util.defdata;

import com.comm.util.bean.BaseCount;
import io.reactivex.observers.DisposableObserver;

import static android.text.TextUtils.isEmpty;

public abstract class DefaultObserver<T> extends DisposableObserver<T> {


    private boolean parseData(T t) {
        if (t instanceof BaseCount) {
            BaseCount bs = (BaseCount) t;
            if (bs.getMeta().getStatusCode() == 0) {
                return true;
            } else if (bs.getMeta().getStatusCode() == -1) {
                if (!isEmpty(bs.getMeta().getDescribe())) {
                    System.out.println(bs.getMeta().getDescribe());
                } else {
                    System.out.println("操作异常，请联系客服处理");
                }
            }
        }
        return false;
    }

    @Override
    public void onNext(T response) {
        if (parseData(response)) {
            onSuccess(response);
        }
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    public abstract void onSuccess(T response);
}
