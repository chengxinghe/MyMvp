package com.cxh.mylogin.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxh.mylogin.R;
import com.cxh.mylogin.mvp.entity.ReceiverChipEntity;

import java.util.List;

/**
 * ChipsNewAdapter
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Chengxinghe
 * @version 1.0
 * @date 2018/7/28 9:12
 */
public class ChipsNewAdapter extends BaseMultiItemQuickAdapter<ReceiverChipEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChipsNewAdapter(List<ReceiverChipEntity> data) {
        super(data);
        addItemType(ReceiverChipEntity.RECEIVER_TEXT, R.layout.item_login_text);
        addItemType(ReceiverChipEntity.RECEIVER_IMG, R.layout.item_login_recycle);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiverChipEntity item) {
        if (helper.getItemViewType() == ReceiverChipEntity.RECEIVER_TEXT) {
            helper.setText(R.id.tv_receiver, item.getReceiverName());
        } else {
            helper.setText(R.id.tv_receiver_name, item.getReceiverName());
            ImageView mIvIcon = helper.getView(R.id.iv_receiver_icon);
            ImageView mIbDelete = helper.getView(R.id.iv_close);
            TextView mTvPosition = helper.getView(R.id.tv_position);
            RequestOptions options = new RequestOptions()
                    .transform(new CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext).load(item.getReceiverIcon())
                    .apply(options).into(mIvIcon);
            helper.addOnClickListener(R.id.iv_close);
            if (item.isDelete()) {
                mIbDelete.setVisibility(View.VISIBLE);
                mTvPosition.setVisibility(View.GONE);
            } else {
                mIbDelete.setVisibility(View.GONE);
                mTvPosition.setVisibility(View.VISIBLE  );
            }

        }

    }
}
