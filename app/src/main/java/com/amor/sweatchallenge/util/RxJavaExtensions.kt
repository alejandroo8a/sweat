package com.amor.sweatchallenge.util

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subscribeOnIoAndObserveOnMainThread(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.subscribeOnDbAndObserveOnMainThread(): Flowable<T> {
    return this.subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
}


fun <T> Single<T>.subscribeOnDbAndObserveOnMainThread(): Single<T> {
    return this.subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation())
}