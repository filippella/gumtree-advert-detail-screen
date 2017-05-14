package com.gumtree.mobile.android.data.pojo;

/**
 * Created by Filippo on 4/5/2017.
 */

public enum AdvertInfoType {

    DEFAULT(-1),

    TYPE_TITLE(0),

    TYPE_LOCATION_AND_PRICE(1),

    TYPE_DETAIL(2),

    TYPE_FINANCE(3),

    TYPE_DESCRIPTION(4),

    TYPE_HISTORY(5),

    TYPE_ADVERTISER_PROFILE(6),

    TYPE_ADS_TYPE_ONE(7),

    TYPE_MAP(8),

    TYPE_ADS_TYPE_TWO(9);

    private final int mId;

    AdvertInfoType(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public static AdvertInfoType fromId(int idType) {
        AdvertInfoType infoType = DEFAULT;
        AdvertInfoType[] advertInfoTypes = values();
        for (int i = 0; i < advertInfoTypes.length; i++) {
            AdvertInfoType advertInfoType = advertInfoTypes[i];
            if (advertInfoType.getId() == idType) {
                infoType = advertInfoType;
            }
        }
        return infoType;
    }
}
