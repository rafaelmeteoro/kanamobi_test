package com.meteoro.kanamobitest.ui.repositories.presentation

import com.meteoro.kanamobitest.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.kanamobitest.ui.repositories.presentation.coordinator.GetRepositoriesCoordinator
import rx.Observable
import rx.Subscription
import javax.inject.Inject

class RepositoriesPresenter @Inject constructor(
        private val getRepositoriesCoordinator: GetRepositoriesCoordinator,
        private val automaticUnsubscriber: AutomaticUnsubscriber
) : RepositoriesContract.Presenter {

    override fun initializeContents() {
        val subscription: Subscription = Observable.just("")
                .compose(getRepositoriesCoordinator)
                .subscribe()
        automaticUnsubscriber.add(subscription)
    }
}