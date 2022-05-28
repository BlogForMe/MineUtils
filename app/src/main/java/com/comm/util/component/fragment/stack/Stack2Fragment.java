package com.comm.util.component.fragment.stack;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.comm.util.R;
import com.comm.util.base.BaseFragment;

/**
 * @author : John
 * @date : 2018/9/5
 */
public class Stack2Fragment extends BaseFragment {

    private Button mBtn;
    private ICallback mListener;

    @Override
    protected void initView(View view) {
        mBtn = view.findViewById(R.id.id_fragment_go_three);

        mBtn.setOnClickListener(v -> {
        });

        view.findViewById(R.id.id_fragment_call_one).setOnClickListener(v->{
            mListener.back("I AM Stack2Fragment");
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_stack_two;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            mListener = (ICallback)context;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        // check if parent Fragment implements listener
        if (getParentFragment() instanceof ICallback) {
            mListener = (ICallback) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }
    }

    interface  ICallback {
        void back(String msg);
    }

}
