package com.meteoro.kanamobitest.core.lifecycle

import android.arch.lifecycle.LifecycleObserver
import rx.Subscription

interface AutomaticUnsubscriber : LifecycleObserver {
    fun add(subscription: Subscription)
}