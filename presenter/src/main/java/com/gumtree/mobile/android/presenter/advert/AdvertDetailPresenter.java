package com.gumtree.mobile.android.presenter.advert;

/**
 * Created by Filippo on 4/5/2017.
 */

public interface AdvertDetailPresenter {

    void onLoadAdvert();

    int onGetFavouriteIcon(boolean checked);

    void onScrollOffsetChanged(boolean offsetChanged);

    void onOpenAdvertHistory();

    void setTitle(String title);
}
