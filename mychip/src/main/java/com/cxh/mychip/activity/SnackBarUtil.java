package com.cxh.mychip.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cxh.mychip.R;

/**
 * SnackBarUtil
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Chengxinghe
 * @version 1.0
 * @date 2018/8/1 13:59
 * @seecom.air.airmessage
 */
public final class SnackBarUtil {
    private Context mContext;
    private Snackbar mSnackbar;

    public SnackBarUtil(Context context, View view, String msg) {
        mContext = context;
        this.mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
    }

    public SnackBarUtil(Context context, View view, String msg, int duration) {
        mContext = context;
        this.mSnackbar = Snackbar.make(view, msg, duration);
    }

    /**
     * 设置文字
     *
     * @param msg 文字
     */
    private void setText(@StringRes int msg) {
        mSnackbar.setText(msg);
    }

    /**
     * 设置右侧文字和监听
     *
     * @param msg      文字
     * @param listener 监听
     */
    private void setActionText(@StringRes int msg, final OnActionClickListener listener) {
        mSnackbar.setAction(msg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
    }

    /**
     * 设置显示时间
     *
     * @param duration 显示时间
     */
    private void setDuration(int duration) {
        mSnackbar.setDuration(duration);
    }

    /**
     * 设置右侧文字颜色
     *
     * @param color 字体颜色
     */
    private void setActionTextColor(int color) {
        mSnackbar.setActionTextColor(color);
    }

    /**
     * 显示
     */
    private void show() {
        mSnackbar.show();
    }


    /**
     * 设置背景和字体颜色
     *
     * @param drawable 背景
     * @param color    字体颜色
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setBackgroundAndColor(Drawable drawable, int color) {
        View snackBarView = mSnackbar.getView();
        snackBarView.setBackground(drawable);
        TextView mTvContent = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        mTvContent.setTextColor(color);
    }

    /**
     * 在某个控件上
     *
     * @param view    控件
     * @param gravity 位置
     */
    private void setGravity(View view, int gravity) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mSnackbar.getView().getLayoutParams());
        params.gravity = gravity;
        params.setMargins(16, 0, 16, view.getHeight());
        mSnackbar.getView().setLayoutParams(params);
    }

    private void setGravity(int gravity) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mSnackbar.getView().getLayoutParams());
        params.gravity = gravity;
        params.setMargins(16, 0, 16, 48);
        mSnackbar.getView().setLayoutParams(params);
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setWarnConfig() {
        setBackgroundAndColor(mContext.getDrawable(R.drawable.round), ContextCompat.getColor(mContext, R.color.colorAccent));
        setGravity(Gravity.BOTTOM);
    }

    /**
     * 设置背景颜色
     *
     * @param color 背景颜色
     */
    private void setBackgroundColor(int color) {
        View snackBarView = mSnackbar.getView();
        snackBarView.setBackgroundColor(color);
    }

    public static final class SnackBarBuilder {
        private SnackBarUtil mSnackBarUtil;

        public SnackBarBuilder(Context context, View view, String msg) {
            mSnackBarUtil = new SnackBarUtil(context, view, msg);
        }

        public SnackBarBuilder(Context context, View view, String msg, int duration) {
            mSnackBarUtil = new SnackBarUtil(context, view, msg, duration);
        }

        /**
         * 设置信息
         *
         * @param text 文字信息
         * @return
         */
        public SnackBarBuilder setText(@StringRes int text) {
            mSnackBarUtil.setText(text);
            return this;
        }

        /**
         * 设置显示时间
         *
         * @param duration 显示时间
         * @return
         */
        public SnackBarBuilder setDuration(int duration) {
            mSnackBarUtil.setDuration(duration);
            return this;
        }

        /**
         * 设置右侧文字
         *
         * @param text     文字
         * @param listener 文字监听
         * @return
         */
        public SnackBarBuilder setActionText(@StringRes int text, OnActionClickListener listener) {
            mSnackBarUtil.setActionText(text, listener);
            return this;
        }

        /**
         * 设置右侧文字颜色
         *
         * @param color 文字颜色
         * @return
         */
        public SnackBarBuilder setActionTextColor(int color) {
            mSnackBarUtil.setActionTextColor(color);
            return this;
        }

        /**
         * 设置背景和文字颜色
         *
         * @param drawable
         * @param color
         * @return
         */
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public SnackBarBuilder setBackgroundAndColor(Drawable drawable, int color) {
            mSnackBarUtil.setBackgroundAndColor(drawable, color);
            return this;
        }

        public SnackBarBuilder setBackgroundColor(int color) {
            mSnackBarUtil.setBackgroundColor(color);
            return this;
        }

        public SnackBarBuilder setGravity(View view,int gravity){
            mSnackBarUtil.setGravity(view,gravity);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public SnackBarBuilder setWarnConfig() {
            mSnackBarUtil.setWarnConfig();
            return this;
        }

        public SnackBarBuilder show() {
            mSnackBarUtil.show();
            return this;
        }

    }


    public interface OnActionClickListener {
        void onClick(View view);
    }


}
