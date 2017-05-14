package com.gumtree.mobile.android.data.protocol;

/**
 * Created by Filippo on 4/5/2017.
 */

public interface AdvertDetailProtocolCallback<R, E> {

    void onDone();

    void onSuccess(R result);

    void onFailure(E error);
}
