package com.gumtree.mobile.android.data.presentation;

import com.gumtree.mobile.android.data.pojo.AdvertInfo;

import java.util.List;

/**
 * Created by Filippo on 4/5/2017.
 */

public interface AdvertDetailView {

    void onTitleChanged(String title);

    void onSuccessfullyLoadAdvert(List<AdvertInfo> result);

    void onLoadAdvertFailure(String errorMessage);

    void onLoadAdvertDone();

    void onShowToast(int resId);

    void openHistoryActivity();
}
