package com.meteoro.kanamobitest.core.lifecycle

import android.arch.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable

class LifeCycleStrategist(private val owner: LifecycleOwner, private val strategy: DisposeStrategy) {

    init {
        owner.lifecycle.addObserver(strategy)
    }

    fun applyStrategy(toDispose: Disposable) {
        strategy.addDisposable(toDispose)
    }
}