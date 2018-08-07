package com.cxh.mychip.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cxh.mychip.R;

/**
 * MyDialogFragment
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Chengxinghe
 * @version 1.0
 * @date 2018/8/2 13:15
 * @seecom.air.airmessage
 */
public class MyDialogFragment extends DialogFragment {
    private Button mBtnCommit;
    private Context mContext;
    private String mContent;
    private TextView mTvContent;
    /**
     * DialogFragment外面背景透明度
     */
    private float mOutsideDimAmount = 0.6f;


    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = mOutsideDimAmount;
            window.setAttributes(windowParams);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        mBtnCommit = view.findViewById(R.id.btn_sure);
        mTvContent = view.findViewById(R.id.tv_dialog_content);
        mTvContent.setText(mContent);
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    public static class MyDialogFragmentBuild {
        MyDialogFragment mMyDialogFragment;

        public MyDialogFragmentBuild setContext(Context context) {
            mMyDialogFragment = new MyDialogFragment();
            mMyDialogFragment.mContext = context;
            return this;
        }

        public MyDialogFragmentBuild setContent(String content) {
            mMyDialogFragment.mContent = content;
            return this;
        }

        public MyDialogFragment create() {
            return mMyDialogFragment;
        }
    }


}
