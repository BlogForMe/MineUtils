package com.comm.util.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者：潇风寒月
 * 原文：https://blog.csdn.net/xfhy_/article/details/78779108
 */
public class PopupWindowUtil implements PopupWindow.OnDismissListener {

    /**
     * PopupWindow放在view左边
     */
    public static final int LEFT = 1000;
    /**
     * PopupWindow放在view右边
     */
    public static final int RIGHT = 1001;
    /**
     * PopupWindow放在view上边
     */
    public static final int TOP = 1002;
    /**
     * PopupWindow放在view下边
     */
    public static final int BOTTOM = 1003;
    /**
     * PopupWindow放在屏幕底部
     */
    public static final int SCREEN_BOTTOM = 1004;
    /**
     * PopupWindow放在屏幕顶部
     */
    public static final int SCREEN_TOP = 1005;
    public static final int SCREEN_CENTER = 1006;
    private final Context mContext;
    private final View mAnchor;
    private final int mWidth;
    private final int mHeight;
    private final int mStyle;
    private final boolean mFocusable;
    private final float mAlpha;
    private final Drawable mBackgroundDrawable;
    private final int mPopupPosition;
    private final float x;
    private final float y;
    private View mView;
    private int mLayoutId = -1;
    private PopupWindow mPopupWindow;

    private PopupWindowUtil(Builder builder) {
        this.mContext = builder.context;
        this.mAnchor = builder.anchor;
        this.mView = builder.showView;
        this.mLayoutId = builder.layoutId;
        this.mWidth = builder.width;
        this.mHeight = builder.height;
        this.mStyle = builder.style;
        this.mFocusable = builder.focusable;
        this.mAlpha = builder.alpha;
        this.mBackgroundDrawable = builder.backgroundDrawable;
        this.mPopupPosition = builder.popupPosition;
        this.x = builder.x;
        this.y = builder.y;
    }

    public PopupWindow show() {
        //1, 将弹出窗口需要展示的布局加载进来
        //View popupView = View.inflate(mContext, mLayoutId, null);

        //2, 创建popupWindow
        //参数:view,宽度,高度,是否能获取焦点
        if (mView != null) {
            mPopupWindow = new PopupWindow(mView, mWidth, mHeight, true);
        } else if (mLayoutId != -1) {
            mView = View.inflate(mContext, mLayoutId, null);
            mPopupWindow = new PopupWindow(mView, mWidth, mHeight, true);
        }
        //3, 设置显示隐藏的动画
        mPopupWindow.setAnimationStyle(mStyle);
        //4, 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //5, 点击空白处时，隐藏掉pop窗口
        mPopupWindow.setFocusable(mFocusable);
        //6, 隐藏PopupWindow下面的背景透明度
        setmBackgroundAlpha(mAlpha);
        //7, 设置popupWindow的背景颜色  这里需要设置成透明的  这里设置成透明的
        mPopupWindow.setBackgroundDrawable(mBackgroundDrawable);
        //8, 添加pop窗口关闭事件
        mPopupWindow.setOnDismissListener(this::onDismiss);
        //位置
        switch (mPopupPosition) {
            case LEFT:
                mPopupWindow.showAsDropDown(mAnchor, -mPopupWindow.getWidth(), -mPopupWindow
                        .getHeight()
                        / 2 - mAnchor.getHeight() / 2);
                break;
            case RIGHT:
                mPopupWindow.showAsDropDown(mAnchor, mAnchor.getWidth(), (-mPopupWindow
                        .getHeight()
                        >> 1) - (mAnchor.getHeight() >> 1));
                break;
            case TOP:
                mPopupWindow.showAsDropDown(mAnchor,
                        Math.abs(mAnchor.getWidth() / 2 - mPopupWindow.getWidth() / 2),
                        -(mPopupWindow.getHeight() + mAnchor.getMeasuredHeight()));
                break;
            case BOTTOM:
                mPopupWindow.showAsDropDown(mAnchor,
                        Math.abs(mAnchor.getWidth() / 2 - mPopupWindow.getWidth() / 2), 0);
                break;
            case SCREEN_BOTTOM:
                mPopupWindow.showAtLocation(mAnchor, Gravity.BOTTOM, -200, 200);
                break;
            case SCREEN_TOP:
                mPopupWindow.showAtLocation(mAnchor, Gravity.TOP, 300, 200);
                break;
            case  SCREEN_CENTER:
                mPopupWindow.showAtLocation(mAnchor, Gravity.CENTER, 0, 0);
                break;
            default:
                mPopupWindow.showAtLocation(mAnchor, Gravity.BOTTOM, 0, 0);
                break;
        }
        return mPopupWindow;
    }

    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     */
    public void setmBackgroundAlpha(float bgAlpha) {
        if (mContext instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) mContext;
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = bgAlpha;
            activity.getWindow().setAttributes(lp);
        }
    }

    @Override
    public void onDismiss() {
        setmBackgroundAlpha(1f);
    }


    /**
     * popupWindow位置类型   替代Java中的枚举类型
     */


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LEFT, RIGHT, TOP, BOTTOM, SCREEN_BOTTOM, SCREEN_TOP,SCREEN_CENTER})
    private @interface PopupPosition {
    }

    public static class Builder {
        private Context context;
        private View anchor;
        private int layoutId = -1;
        private View showView;
        private int width;
        private int height;
        private int style;
        private boolean focusable;
        private float alpha;
        private Drawable backgroundDrawable;
        private int popupPosition;
        private float x;
        private float y;

        public Builder context(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder anchor(@NonNull View view) {
            this.anchor = view;
            return this;
        }

        public Builder layout(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder view(@NonNull View view) {
            this.showView = view;
            return this;
        }

        /**
         * PopupWindow宽度
         */
        public Builder width(int width) {
            this.width = width;
            return this;
        }

        /**
         * PopupWindow高度
         */
        public Builder height(int height) {
            this.height = height;
            return this;
        }

        /**
         * PopupWindow style
         */
        public Builder style(int style) {
            this.style = style;
            return this;
        }

        /**
         * PopupWindow点击外部是否取消
         */
        public Builder focusable(boolean focusable) {
            this.focusable = focusable;
            return this;
        }

        public Builder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }


        public Builder setX(float x) {
            this.x = x;
            return this;
        }

        public Builder setY(float y) {
            this.y = y;
            return this;
        }

        /**
         * 背景
         */
        public Builder backgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder position(@PopupPosition int popupPosition) {
            this.popupPosition = popupPosition;
            return this;
        }

        public PopupWindowUtil build() {
            if (context == null) {
                throw new IllegalArgumentException("Context can't be null!");
            }
            if (anchor == null) {
                throw new IllegalArgumentException("anchor View can't be null!");
            }
            if (showView == null && layoutId == -1) {
                throw new IllegalArgumentException("showView can't be null!");
            }
            if (backgroundDrawable == null) {
                backgroundDrawable = new ColorDrawable();
            }
            return new PopupWindowUtil(this);
        }
    }


}
