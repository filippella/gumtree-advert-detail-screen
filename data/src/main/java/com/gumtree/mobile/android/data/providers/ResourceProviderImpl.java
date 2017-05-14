package com.gumtree.mobile.android.data.providers;

import com.gumtree.mobile.android.data.R;

import javax.inject.Inject;

/**
 * Created by Filippo on 4/5/2017.
 */

public class ResourceProviderImpl implements ResourceProvider {

    @Inject
    public ResourceProviderImpl() {}

    @Override
    public int provideWhiteStarBorder() {
        return R.mipmap.ic_star_border_white_24dp;
    }

    @Override
    public int provideWhiteStar() {
        return R.mipmap.ic_star_white_24dp;
    }
}
