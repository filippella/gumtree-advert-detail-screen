package com.gumtree.mobile.android.data.base;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Filippo on 4/5/2017.
 */

public abstract class BaseProviderObservable<T> implements Observable.OnSubscribe<T> {

    public Observable<T> create() {
        return Observable.create(BaseProviderObservable.this);
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {
        try {
            T work = doBackgroundWork();
            subscriber.onNext(work);
            subscriber.onCompleted();
        } catch(Exception e) {
            subscriber.onError(e);
        }
    }

    protected abstract T doBackgroundWork() throws Exception;
}
