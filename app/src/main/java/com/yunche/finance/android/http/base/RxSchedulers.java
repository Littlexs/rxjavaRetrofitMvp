package com.yunche.finance.android.http.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<ResultBody<T>, ResultBody<T>> compose(final LifecycleTransformer<ResultBody<T>> transformer) {
        return new ObservableTransformer<ResultBody<T>, ResultBody<T>>() {
            @Override
            public ObservableSource<ResultBody<T>> apply(Observable<ResultBody<T>> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                    //请求前的操作
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(transformer);
            }
        };
    }
}