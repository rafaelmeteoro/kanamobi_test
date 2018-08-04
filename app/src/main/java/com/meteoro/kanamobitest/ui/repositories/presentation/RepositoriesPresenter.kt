package com.meteoro.kanamobitest.ui.repositories.presentation

import com.meteoro.kanamobitest.core.lifecycles.AutomaticUnsubscriber
import com.meteoro.kanamobitest.ui.repositories.presentation.coordinator.GetMoreRepositoriesCoordinator
import com.meteoro.kanamobitest.ui.repositories.presentation.coordinator.GetRepositoriesCoordinator
import rx.Observable
import rx.Subscription
import javax.inject.Inject

class RepositoriesPresenter @Inject constructor(
        private val getRepositoriesCoordinator: GetRepositoriesCoordinator,
        private val getMoreRepositoriesCoordinator: GetMoreRepositoriesCoordinator,
        private val automaticUnsubscriber: AutomaticUnsubscriber
) : RepositoriesContract.Presenter {

    override fun initializeContents() {
        val subscription: Subscription = Observable.just(1)
                .compose(getRepositoriesCoordinator)
                .subscribe()
        automaticUnsubscriber.add(subscription)
    }

    override fun getMoreItems(page: Int) {
        val subscription: Subscription = Observable.just(page)
                .compose(getMoreRepositoriesCoordinator)
                .subscribe()
        automaticUnsubscriber.add(subscription)
    }
}