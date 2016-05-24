package com.wxxiaomi.ming.teachingoffice2.model.impl.base;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 12262 on 2016/5/19.
 */
public class BaseImpl {
    public static <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            subscriber.onNext(func.call());
                        } catch (Exception ex) {
                            subscriber.onError(ex);
                        }
                    }
                });
    }
}
