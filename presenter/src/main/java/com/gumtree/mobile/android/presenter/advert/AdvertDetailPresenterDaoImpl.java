package com.gumtree.mobile.android.presenter.advert;

import com.gumtree.mobile.android.data.pojo.AdvertInfo;
import com.gumtree.mobile.android.data.presentation.AdvertDetailView;
import com.gumtree.mobile.android.data.protocol.AdvertDetailProtocolCallback;
import com.gumtree.mobile.android.presenter.R;
import com.gumtree.mobile.android.presenter.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Filippo on 4/5/2017.
 */

public class AdvertDetailPresenterDaoImpl extends BasePresenter implements AdvertDetailPresenter  {

    @Inject protected AdvertDetailView mDetailView;

    private String mTitle;

    @Inject
    public AdvertDetailPresenterDaoImpl() {
    }

    @Override
    public void onLoadAdvert() {
        mAdvertDetailProvider.onLoadAdvert(new AdvertDetailProtocolCallback<List<AdvertInfo>, Throwable>() {
            @Override
            public void onDone() {
                mDetailView.onLoadAdvertDone();
            }

            @Override
            public void onSuccess(List<AdvertInfo> result) {
                mDetailView.onSuccessfullyLoadAdvert(result);
            }

            @Override
            public void onFailure(Throwable error) {
                mDetailView.onLoadAdvertFailure(error.getMessage());
            }
        });
    }

    @Override
    public int onGetFavouriteIcon(boolean checked) {
        mDetailView.onShowToast(checked ? R.string.label_removed_from_favourites : R.string.label_added_to_favourites);
        if(checked) {
            return mResourceProvider.provideWhiteStarBorder();
        } else {
            return mResourceProvider.provideWhiteStar();
        }
    }

    @Override
    public void onScrollOffsetChanged(boolean offsetChanged) {
        if (mTitle == null) {
            return;
        }
        String title = " ";
        if(offsetChanged) {
           title = mTitle;
        }
        mDetailView.onTitleChanged(title);
    }

    @Override
    public void onOpenAdvertHistory() {
        mDetailView.openHistoryActivity();
    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
    }
}
