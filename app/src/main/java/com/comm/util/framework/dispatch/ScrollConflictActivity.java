package com.comm.util.framework.dispatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.comm.util.R;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 滑动冲突处理
 * https://www.jianshu.com/p/982a83271327
 */
public class ScrollConflictActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_confilc);
        initViews();
        initData(true);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewpager);
        mViews = new ArrayList<>();
    }

    private void initData(final boolean isListView) {
        //初始化mViews列表
        Flowable.just("view1", "view2", "view3", "view4").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                View view;
                if (isListView) {
                    ListView listView = new ListView(ScrollConflictActivity.this);
                    final List<String> datas = new LinkedList<>();
                    //初始化数据，datas ,data0 ...data49
                    Flowable.range(0, 50).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            datas.add("data " + integer);
                        }
                    });
                    //初始化adapter
                    ArrayAdapter<String> adatper = new ArrayAdapter<>(ScrollConflictActivity.this
                            , android.R.layout.simple_list_item_1, datas);
                    listView.setAdapter(adatper);
                    view = listView;
                } else {
                    //初始化TextView
                    TextView textView = new TextView(ScrollConflictActivity.this);
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(s);

//                    textView.setClickable(true); //判断能否滑动
                    //将TextView赋值给当前View
                    view = textView;
                }
                mViews.add(view);
            }
        });

//        mViewPager.setAdapter(new BasePagerAdapter<View>(mViews));

    }
}
