/**
 *
 * ClassName:      SimpleItemDecoration
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/12/19 8:21 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/12/19 8:21 PM
 * UpdateRemark:   Modify the description
 */

package com.comm.util.ui.recycleview.divider.line

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//    作者：重拾丢却的梦
//    链接：https://juejin.cn/post/6844903855335931911
//    来源：稀土掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
class SimpleItemDecoration : RecyclerView.ItemDecoration() {
    /**
     * @param outRect   全为0的rect，用来指定偏移区域
     * @param view      指RecyclerView中的Item
     * @param parent    指RecyclerView本身
     * @param state     状态
     */

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
//        if (parent.getChildAdapterPosition(view) == 0) {
        outRect.top = 0
        outRect.left = 0
        outRect.right = 40
        outRect.bottom = 0
//        }
    }
}