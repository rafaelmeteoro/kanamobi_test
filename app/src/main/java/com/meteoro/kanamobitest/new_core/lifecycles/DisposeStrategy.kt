package com.meteoro.kanamobitest.new_core.lifecycles

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposeStrategy : LifecycleObserver {

    private val composite = CompositeDisposable()

    fun addDisposable(toDispose: Disposable) {
        composite.add(toDispose)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        composite.dispose()
    }
}