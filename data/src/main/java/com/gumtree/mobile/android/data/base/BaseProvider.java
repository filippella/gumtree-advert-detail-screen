package com.gumtree.mobile.android.data.base;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Filippo on 4/5/2017.
 */

public abstract class BaseProvider {

    protected <R> void subscribe(Observable<R> observable, Observer<R> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
