package com.comm.util.ui.tab;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.comm.util.R;
import com.google.android.material.tabs.TabLayout;

/**
 * https://jonzhou.com/2017/12/26/TabLayout/
 * 动态Tab和样式修改
 */
public class TabLayoutActivity extends AppCompatActivity {

    String[] tabTitles = {"血压", "血糖", "拍照", "血氧", "足感", "足照"};
    Fragment firstFragment, secondFragment;
    private TabLayout tabLayout;
    private FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tabLayout = findViewById(R.id.tb_layout);
        flContent = findViewById(R.id.fl_content);
        initTab();

//        findViewById(R.id.bt_finish).setOnClickListener(v -> {
//            for (int i = 0; i < 2; i++) {
//                TextView itemTextView = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tv_item_title);
//                itemTextView.setTextColor(Color.parseColor("#EEB125"));
//                itemTextView.setBackgroundResource(R.drawable.bg_circle_tab_yellow_w);
//            }
//        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTab() {
        for (int i = 0; i < tabTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(R.layout.tab_item);
            TextView tvItemTitle = tab.getCustomView().findViewById(R.id.tv_item_title);
            tvItemTitle.setText(tabTitles[i]);
            if (i == tabTitles.length - 1) {
                tab.getCustomView().findViewById(R.id.view_line).setVisibility(View.GONE);
            }
        }
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if (firstFragment == null) {
                    firstFragment = HomeFragment.newInstance();
                    ft.add(R.id.fl_content, firstFragment, HomeFragment.class.getName());
                } else {
                    ft.show(firstFragment);
                }
                break;
            case 1:
                if (secondFragment == null) {
                    secondFragment = HealthFragment.newInstance();
                    ft.add(R.id.fl_content, secondFragment, HealthFragment.class.getName());
                } else {
                    ft.show(secondFragment);
                }
                break;
        }
        ft.addToBackStack(null);
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (firstFragment != null) {
            ft.hide(firstFragment);
        }
        if (secondFragment != null) {
            ft.hide(secondFragment);
        }
    }

}
