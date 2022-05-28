package com.comm.util.ui.recycleview.xiangxue;

import java.util.Stack;

import android.view.View;

//https://www.bilibili.com/video/BV1Fi4y1x7p5?from=search&seid=15431064704441917260
class Recycler {
    private static final String TAG = "Recycler";
    //集合  ===》 list hashmap stack数组
    //stack  一个集合
    private final Stack<View>[] views;

    Recycler(int count) {
        views = new Stack[count];
        for (int i = 0; i < count; i++) {
            views[i] = new Stack<View>();
        }
    }

    //    回收池 存 取
    View getRecyclerdView(int type) {

        try {
            return views[type].pop();
        } catch (Exception e) {
            return null;
        }
    }

    void addRecyclerdView(View view, int type) {
        views[type].push(view);
    }

}
