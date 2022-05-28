package com.comm.util.ui.recycleview.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.comm.util.R;

/**
 * @author : John
 * @date : 2018/8/20
 */
public class ItemAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //定义一个集合，接收从Activity中传递过来的数据和上下文
    private final List<String> mList;
    private final Context mContext;

    public ItemAdpater(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    //得到child的数量
    @Override
    public int getItemCount() {
//        return 1;
        return mList.size();
    }

    //创建ChildView
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_view_decoration, parent, false);
        return new MyHolder(layout);
    }

    //将数据绑定到每一个childView中
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            final String itemText = mList.get(position);
            ((MyHolder) holder).tv.setText(itemText);
        }
    }

    // 通过holder的方式来初始化每一个ChildView的内容
    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.list_item);
        }
    }
}
