package com.gumtree.mobile.android.data.providers;

import com.gumtree.mobile.android.data.protocol.AdvertDetailProtocolCallback;

/**
 * Created by Filippo on 4/5/2017.
 */

public interface AdvertDetailProvider {

    void onLoadAdvert(final AdvertDetailProtocolCallback callback);
}
