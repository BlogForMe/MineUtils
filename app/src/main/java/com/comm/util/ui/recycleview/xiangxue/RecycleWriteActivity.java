package com.comm.util.ui.recycleview.xiangxue;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.comm.util.R;

public class RecycleWriteActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    int count = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView = findViewById(R.id.table);
        recyclerView.setAdapter(new MyAdapter(this));


    }

    class MyAdapter implements RecyclerView.Adapter {
        private final int height;
        LayoutInflater inflater;

        public MyAdapter(Context context) {
            Resources resources = context.getResources();
            height = resources.getDimensionPixelSize(R.dimen.item_height);
            Log.e(TAG, "MyAdapter: 高度" + height);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View onCreateViewHolder(int position, View converView, ViewGroup parent) {
            if (position%2 == 0){
                if (converView == null){
                    converView = inflater.inflate(R.layout.item_table1, parent, false);
                }
                TextView textView = converView.findViewById(R.id.text1);
                textView.setText("第" + position + "行");
                return converView;
            }else{
                if (converView == null){
                    converView = inflater.inflate(R.layout.item_image, parent, false);
                }
                return converView;
            }
        }

        @Override
        public View onBinderViewHodler(int position, View converView, ViewGroup parent) {
            if (position%2 == 0){
                if (converView == null){
                    converView = inflater.inflate(R.layout.item_table1, parent, false);
                }
                TextView textView = converView.findViewById(R.id.text1);
                textView.setText("第" + position + "行");
                return converView;
            }else{
                if (converView == null){
                    converView = inflater.inflate(R.layout.item_image, parent, false);
                }
                return converView;
            }

        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public int getHeight(int index) {
            return height;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int row) {
            if (row % 2 == 0)
                return 0;
            else return 1;
        }
    }

}
