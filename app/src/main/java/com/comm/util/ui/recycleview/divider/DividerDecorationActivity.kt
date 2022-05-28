package com.comm.util.ui.recycleview.divider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comm.util.R
import com.comm.util.ui.recycleview.adapter.ItemAdpater
import com.comm.util.ui.recycleview.divider.line.DividerGridItemDecoration
import kotlinx.android.synthetic.main.activity_divider_decoration.*

class DividerDecorationActivity : AppCompatActivity() {
    //定义RecyclerView
    private val mRecyclerView: RecyclerView? = null

    //定义一个List集合，用于存放RecyclerView中的每一个数据
    private var mData: MutableList<String>? = null

    //定义一个Adapter
    private var mAdapter: ItemAdpater? = null

    //定义一个LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divider_decoration)

        //RecyclerView三步曲+LayoutManager
        initData()
        mAdapter = ItemAdpater(this, mData)
//        val mLayoutManager =
        recycler_view.layoutManager = GridLayoutManager(this, 4)
        //这句就是添加我们自定义的分隔线
        val mDivider = DividerGridItemDecoration(this)
        recycler_view.addItemDecoration(mDivider)

        recycler_view.adapter = mAdapter
    }

    //初始化加载到RecyclerView中的数据, 我这里只是给每一个Item添加了String类型的数据
    private fun initData() {
        mData = ArrayList()
        for (i in 0..19) {
            mData!!.add("Item$i")
        }
    }

}
