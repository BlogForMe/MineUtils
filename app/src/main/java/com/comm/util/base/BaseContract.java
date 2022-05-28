package com.comm.util.base;

/**
 * Created by A on 2018/3/19.
 */

public class BaseContract {

    public interface BasePresenter<T extends BaseContract.BaseView> {
        void attachView(T view);

        void detachView();

    }

    public interface BaseView {
        //显示进度中
        void showLoading();
        //隐藏进度
        void hideLoading();

        /**
         * 绑定生命周期
         *
         * @param <T>
         * @return
         */
//        <T> LifecycleTransformer<T> bindToLife();
    }

}
