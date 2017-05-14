package com.gumtree.mobile.android.data.providers;

import com.gumtree.mobile.android.data.base.BaseProvider;
import com.gumtree.mobile.android.data.observable.AdvertDetailObservable;
import com.gumtree.mobile.android.data.pojo.AdvertInfo;
import com.gumtree.mobile.android.data.protocol.AdvertDetailProtocolCallback;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by Filippo on 4/5/2017.
 */

public class AdvertDetailDaoProviderImpl extends BaseProvider implements AdvertDetailProvider {

    @Inject protected AdvertDetailObservable mAdvertDetailObservable;

    @Inject
    public AdvertDetailDaoProviderImpl() {}

    @Override
    public void onLoadAdvert(final AdvertDetailProtocolCallback callback) {
        subscribe(mAdvertDetailObservable.create(), new Observer<List<AdvertInfo>>() {
            @Override
            public void onCompleted() {
                callback.onDone();
            }

            @Override
            public void onError(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onNext(List<AdvertInfo> advertInfos) {
                callback.onSuccess(advertInfos);
            }
        });
    }
}
