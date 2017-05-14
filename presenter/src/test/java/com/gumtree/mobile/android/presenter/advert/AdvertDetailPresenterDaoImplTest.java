package com.gumtree.mobile.android.presenter.advert;

import com.gumtree.mobile.android.data.pojo.AdvertInfo;
import com.gumtree.mobile.android.data.presentation.AdvertDetailView;
import com.gumtree.mobile.android.data.protocol.AdvertDetailProtocolCallback;
import com.gumtree.mobile.android.data.providers.AdvertDetailProvider;
import com.gumtree.mobile.android.data.providers.ResourceProvider;
import com.gumtree.mobile.android.presenter.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Filippo on 4/6/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdvertDetailPresenterDaoImplTest {

    public static final String TEST_ERROR_MSG = "Error";
    public static final String TEST_TITLE = "Title";

    @InjectMocks private AdvertDetailPresenterDaoImpl mPresenterDao;

    @Mock protected ResourceProvider mResourceProvider;
    @Mock protected AdvertDetailProvider mAdvertDetailProvider;
    @Mock protected AdvertDetailView mView;

    @Captor private ArgumentCaptor<AdvertDetailProtocolCallback<List<AdvertInfo>, Throwable>> mCallbackCaptor;

    @Test
    public void onLoadAdvert_Success() throws Exception {
        mPresenterDao.onLoadAdvert();
        verify(mAdvertDetailProvider, times(1)).onLoadAdvert(mCallbackCaptor.capture());
        mCallbackCaptor.getValue().onSuccess(new ArrayList<AdvertInfo>());
        mCallbackCaptor.getValue().onDone();
        verify(mView, times(1)).onSuccessfullyLoadAdvert(ArgumentMatchers.<AdvertInfo>anyList());
        verify(mView, times(1)).onLoadAdvertDone();
    }

    @Test
    public void onLoadAdvert_Failed() throws Exception {
        mPresenterDao.onLoadAdvert();
        verify(mAdvertDetailProvider, times(1)).onLoadAdvert(mCallbackCaptor.capture());
        mCallbackCaptor.getValue().onFailure(new Throwable(TEST_ERROR_MSG));
        verify(mView, times(1)).onLoadAdvertFailure(eq(TEST_ERROR_MSG));
        verify(mView, never()).onLoadAdvertDone();
    }

    @Test
    public void onGetFavouriteIcon_When_True() throws Exception {
        mPresenterDao.onGetFavouriteIcon(true);
        verify(mView, times(1)).onShowToast(R.string.label_removed_from_favourites);
        verify(mResourceProvider, times(1)).provideWhiteStarBorder();
    }

    @Test
    public void onGetFavouriteIcon_When_False() throws Exception {
        mPresenterDao.onGetFavouriteIcon(false);
        verify(mView, times(1)).onShowToast(R.string.label_added_to_favourites);
        verify(mResourceProvider, times(1)).provideWhiteStar();
    }

    @Test
    public void onScrollOffsetChanged_WHEN_True() throws Exception {
        mPresenterDao.setTitle(TEST_TITLE);
        mPresenterDao.onScrollOffsetChanged(true);
        verify(mView, times(1)).onTitleChanged(eq(TEST_TITLE));
    }

    @Test
    public void onScrollOffsetChanged_WHEN_False() throws Exception {
        mPresenterDao.setTitle(TEST_TITLE);
        mPresenterDao.onScrollOffsetChanged(false);
        verify(mView, times(1)).onTitleChanged(eq(" "));
    }

    @Test
    public void onOpenAdvertHistory() throws Exception {
        mPresenterDao.onOpenAdvertHistory();
        verify(mView, times(1)).openHistoryActivity();
    }
}