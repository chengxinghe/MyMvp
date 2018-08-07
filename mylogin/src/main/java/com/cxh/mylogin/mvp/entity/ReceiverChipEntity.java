package com.cxh.mylogin.mvp.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * ReceiverChipEntity
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Chengxinghe
 * @version 1.0
 * @date 2018/7/28 9:36
 * @see com.air.airmessage
 */
public class ReceiverChipEntity implements MultiItemEntity {
    public static final int RECEIVER_TEXT = 1;
    public static final int RECEIVER_IMG = 2;
    private int itemType;
    private String receiverName;
    private int receiverIcon;
    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getReceiverIcon() {
        return receiverIcon;
    }

    public void setReceiverIcon(int receiverIcon) {
        this.receiverIcon = receiverIcon;
    }

    public ReceiverChipEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
