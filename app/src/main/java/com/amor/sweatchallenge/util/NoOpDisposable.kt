package com.amor.sweatchallenge.util

import io.reactivex.disposables.Disposable

class NoOpDisposable : Disposable {

    override fun isDisposed(): Boolean {
        return true
    }

    override fun dispose() {
        //No-op
    }

}