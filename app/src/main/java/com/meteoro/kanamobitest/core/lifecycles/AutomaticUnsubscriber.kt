package com.meteoro.kanamobitest.core.lifecycles

import android.arch.lifecycle.LifecycleObserver
import rx.Subscription

interface AutomaticUnsubscriber : LifecycleObserver {
    fun add(subscription: Subscription)
}