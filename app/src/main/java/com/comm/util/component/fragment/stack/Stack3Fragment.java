package com.comm.util.component.fragment.stack;

import android.view.View;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;
import com.comm.util.R;
import com.comm.util.base.BaseFragment;

/**
 * @author : John
 * @date : 2018/9/5
 */
public class Stack3Fragment extends BaseFragment {

    private Button mBtn;


    @Override
    protected void initView(View view) {
        mBtn = view.findViewById(R.id.id_fragment_one_btn);
        mBtn.setOnClickListener(v -> {
            FragmentManager fm = getChildFragmentManager();
            fm.popBackStack();
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_stack_three;
    }
}
