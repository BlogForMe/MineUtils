package com.comm.util.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by jon on 12/9/17.
 */

public abstract class BaseFragment extends Fragment {
    public static final String EXTRA_NAME_1 = "extra_name_1";
    public static final String FRAG_PARAMS_01 = "FRAG_PARAMS_01";
    public static final String FRAG_PARAMS_02 = "FRAG_PARAMS_02";
    public static final String PARAMS_01 = "PARAMS_01";
    public static final String PARAMS_02 = "PARAMS_02";
    public static final String PARAMS_03 = "PARAMS_03";
    public static final String PARAMS_04 = "PARAMS_04";
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Context mContext;
    protected View rootView;
    protected Activity mActivity;
    String TAG = getClass().getSimpleName() + " LaunchModeFragment";
    private Unbinder unbinder;


//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        isPrepared = true;
//        lazyLoad();
//        Timber.tag(TAG).d("onStart() ");
//    }
    //  http://www.10tiao.com/html/565/201702/2247483988/1.html
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            int view = setLayoutId();
            if (view != 0) {
                rootView = inflater.inflate(view, container, false);
            }
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        unbinder = ButterKnife.bind(this, rootView);

        initView(rootView);
        Timber.tag(TAG).d("onCreateView()");
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {

    }

    protected void onVisible() {
        lazyLoad();
    }

    private void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        requestData();
    }

    /**
     * 请求数据
     */
    protected void requestData() {
    }

    protected void initView(View rootView) {

    }

    protected abstract int setLayoutId();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);   //为什么要放在这里  否则有些手机会崩
        Timber.tag(TAG).d("onAttach(Context context)");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Timber.tag(TAG).d("onHiddenChanged hidden   " + hidden);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.tag(TAG).d("onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.tag(TAG).d("onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG).d("onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG).d("onPause() ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.tag(TAG).d("onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Timber.tag(TAG).d("onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.tag(TAG).d("onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Timber.tag(TAG).d("onDetach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG).d("onCreate() savedInstanceState    " + savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}
