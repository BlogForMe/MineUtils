package com.comm.util.ui.recycleview;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.comm.util.R;
import com.comm.util.bean.RecipeBean;

import static com.comm.util.bean.RecipeBean.TYPE_BOTTOM;
import static com.comm.util.bean.RecipeBean.TYPE_ITEM;

/**
 * recycleview多布局
 * ScrollView嵌套RecyclerView
 */
public class RecycleViewScrollActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_scroll);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<RecipeBean> reList = new LinkedList<>();
        for (int i=0;i<5;i++){
            RecipeBean recipeBean = null;
            if (i==4){
                recipeBean = new RecipeBean(TYPE_BOTTOM);
            }else {
                recipeBean = new RecipeBean(TYPE_ITEM);
            }
            reList.add(recipeBean);
        }
        ScrollAdapter scrollAdapter = new ScrollAdapter(reList);
        recyclerView.setAdapter(scrollAdapter);


    }

}
