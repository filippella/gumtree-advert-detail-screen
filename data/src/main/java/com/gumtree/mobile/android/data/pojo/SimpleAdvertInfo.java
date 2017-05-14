package com.gumtree.mobile.android.data.pojo;

/**
 * Created by Filippo on 4/5/2017.
 */

public class SimpleAdvertInfo<M> implements AdvertInfo<M> {

    private final AdvertInfoType mInfoType;
    private M mModel;

    public SimpleAdvertInfo(AdvertInfoType infoType) {
        this(infoType, null);
    }

    public SimpleAdvertInfo(AdvertInfoType infoType, M model) {
        mInfoType = infoType;
        mModel = model;
    }

    public void setModel(M model) {
        mModel = model;
    }

    @Override
    public AdvertInfoType getType() {
        return mInfoType;
    }

    @Override
    public M getModel() {
        return mModel;
    }
}
