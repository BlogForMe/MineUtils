package com.comm.util.framework.dispatch.widget;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;

public class BasePagerAdapter<T extends View> extends PagerAdapter {
    private final List<T> mList;

    public BasePagerAdapter(List<T> list) {
        mList = list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }
}
