package com.comm.util.ui.recycleview.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.comm.util.R;
import com.comm.util.ui.recycleview.data.entity.Status;
import com.comm.util.utils.SpannableStringUtils;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
//            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(Utils.getContext().getResources().getColor(R.color.clickspan_color));
//            ds.setUnderlineText(true);
        }
    };


    public PullToRefreshAdapter() {
        super(R.layout.layout_animation, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        String msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        String mdata = item.getUserName() + "     " + item.getCreatedAt();
        ((TextView) helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(mdata).append("landscapes and nedes")/*.setClickSpan(clickableSpan)*/.create());
        ((TextView) helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
    }
}
