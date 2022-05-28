package com.comm.util.ui.recycleview.divider

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.comm.util.databinding.ActivityLineElementBinding
import com.comm.util.ui.recycleview.adapter.ItemAdpater
import com.comm.util.ui.recycleview.divider.line.SimpleItemDecoration

// learning how to write recycleview divider  line
class LineElementActivity : AppCompatActivity() {

    val paint = Paint()

    private lateinit var biding: ActivityLineElementBinding

    private var mData: MutableList<String>? = null

    private var mAdapter: ItemAdpater? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityLineElementBinding.inflate(layoutInflater)
        setContentView(biding.root)
        initData()
        mAdapter = ItemAdpater(this, mData)
//        biding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        biding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //这句就是添加我们自定义的分隔线
//        val mDivider = DividerGridItemDecoration(this)
        val mDivider = SimpleItemDecoration()
//        val mDivider = MyItemDivider(this, VERTICAL_LIST)
        biding.recyclerView.addItemDecoration(mDivider)

//        biding.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
//            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//                super.onDraw(c, parent, state)
//                paint.color = (Color.BLUE)
//                c.drawRect(0F, 0f, 400f, 200f, paint)
//            }
//
//            override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//                super.onDrawOver(c, parent, state)
//                paint.color = Color.RED
//                c.drawRect(0F, 0f, 200f, 100f, paint)
//            }
//
//            override fun getItemOffsets(
//                outRect: Rect,
//                view: View,
//                parent: RecyclerView,
//                state: RecyclerView.State
//            ) {
//                super.getItemOffsets(outRect, view, parent, state)
//                outRect.set(20, 100, 0, 20)
//            }
//        })

        biding.recyclerView.adapter = mAdapter

    }

    //初始化加载到RecyclerView中的数据, 我这里只是给每一个Item添加了String类型的数据
    private fun initData() {
        mData = ArrayList()
        for (i in 0..7) {
            mData!!.add("Item$i")
        }
    }
}