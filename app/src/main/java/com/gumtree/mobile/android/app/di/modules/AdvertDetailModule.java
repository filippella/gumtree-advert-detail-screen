package com.gumtree.mobile.android.app.di.modules;

import com.gumtree.mobile.android.app.di.scopes.PerActivity;
import com.gumtree.mobile.android.data.presentation.AdvertDetailView;
import com.gumtree.mobile.android.data.providers.ResourceProvider;
import com.gumtree.mobile.android.data.providers.ResourceProviderImpl;
import com.gumtree.mobile.android.data.providers.AdvertDetailDaoProviderImpl;
import com.gumtree.mobile.android.data.providers.AdvertDetailProvider;
import com.gumtree.mobile.android.presenter.advert.AdvertDetailPresenter;
import com.gumtree.mobile.android.presenter.advert.AdvertDetailPresenterDaoImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Filippo on 4/5/2017.
 */
@Module
public class AdvertDetailModule {

    private final AdvertDetailView mAdvertView;

    public AdvertDetailModule(AdvertDetailView advertView) {
        mAdvertView = advertView;
    }

    @PerActivity
    @Provides
    AdvertDetailPresenter provideAdvertPresenter(AdvertDetailPresenterDaoImpl presenter) {
        return presenter;
    }

    @PerActivity
    @Provides
    AdvertDetailProvider provideDaoProvider(AdvertDetailDaoProviderImpl provider) {
        return provider;
    }

    @PerActivity
    @Provides
    ResourceProvider provideResourceProvider(ResourceProviderImpl provider) {
        return provider;
    }

    @PerActivity
    @Provides
    AdvertDetailView provideAdvertView() {
        return mAdvertView;
    }
}
