package com.gumtree.mobile.android.app.di.components;

import com.gumtree.mobile.android.app.activities.AdvertDetailActivity;
import com.gumtree.mobile.android.app.di.modules.AdvertDetailModule;
import com.gumtree.mobile.android.app.di.scopes.PerActivity;

import dagger.Component;

/**
 * Created by Filippo on 4/5/2017.
 */
@PerActivity
@Component(modules = AdvertDetailModule.class)
public interface AdvertDetailComponent {

    void inject(AdvertDetailActivity activity);
}
