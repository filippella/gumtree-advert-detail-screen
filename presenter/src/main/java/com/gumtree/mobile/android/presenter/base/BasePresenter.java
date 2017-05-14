package com.gumtree.mobile.android.presenter.base;

import com.gumtree.mobile.android.data.providers.ResourceProvider;
import com.gumtree.mobile.android.data.providers.AdvertDetailProvider;

import javax.inject.Inject;

/**
 * Created by Filippo on 4/5/2017.
 */

public abstract class BasePresenter {

    @Inject protected ResourceProvider mResourceProvider;
    @Inject protected AdvertDetailProvider mAdvertDetailProvider;
}
