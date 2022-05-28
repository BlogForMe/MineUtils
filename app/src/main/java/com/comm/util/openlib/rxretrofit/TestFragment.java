package com.comm.util.openlib.rxretrofit;

import android.view.View;
import android.widget.Button;
import butterknife.OnClick;
import com.comm.util.R;
import com.comm.util.base.BaseFragment;

/**
 * Created by jon on 3/30/18.
 */

public class TestFragment extends BaseFragment {
    @Override
    protected void initView(View view) {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_test;
    }

    @OnClick(R.id.error_return)
    public void error_return(Button button) {
        RxOperator.testOnErrorReturn();
    }


    @OnClick(R.id.error_resume_next)
    public void error_resume_next(Button button) {
        RxOperator.testOnErrorResumeReturn();
    }

    @OnClick(R.id.test_retry)
    public void test_retry(Button button) {
        RxOperator.testRetry();
    }

    @OnClick(R.id.retry_when)
    public  void  retry_when(Button button){
        RxOperator.testRetryWhen();
    }

}
