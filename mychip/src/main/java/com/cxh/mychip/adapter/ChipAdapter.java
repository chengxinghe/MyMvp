package com.cxh.mychip.adapter;

import android.support.annotation.Nullable;
import android.support.design.chip.Chip;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxh.mychip.R;

import java.util.List;

/**
 * ChipAdapter
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Chengxinghe
 * @version 1.0
 * @date 2018/7/30 13:19
 * @seecom.air.airmessage
 */
public class ChipAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ChipAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Chip mChip = helper.getView(R.id.chip);
        mChip.setChipText(item);
    }

}
