package com.comm.util.ui.viewstub;

import android.view.ViewStub;
import butterknife.OnClick;
import com.comm.util.R;
import com.comm.util.base.BaseActivity;

public class ViewStubActivity extends BaseActivity {

    private ViewStub vsContent;

    @Override
    protected void initView() {
        vsContent = findViewById(R.id.vs_content);

    }

    //@Override
    //protected int setLayoutId() {
    //    return R.layout.activity_view_stub;
    //}

    @OnClick(R.id.bt_viewstub)
    public void btViewStub() {
        vsContent.inflate();
    }

}
