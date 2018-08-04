package com.meteoro.kanamobitest.core.lifecycles

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.meteoro.kanamobitest.core.lifecycles.AutomaticUnsubscriber
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class LifecycleUnsubscriber @Inject constructor(private val owner: LifecycleOwner) : AutomaticUnsubscriber {

    private val subscription: CompositeSubscription = CompositeSubscription()

    init {
        owner.lifecycle.addObserver(this)
    }

    override fun add(subscription: Subscription) {
        this.subscription.add(subscription)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        subscription.unsubscribe()
    }
}