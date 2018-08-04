package com.meteoro.kanamobitest.ui.pullrequests.presentation

import com.meteoro.kanamobitest.core.lifecycles.AutomaticUnsubscriber
import com.meteoro.kanamobitest.ui.pullrequests.presentation.coordinator.GetPullRequestsCoordinator
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import rx.Observable
import rx.Subscription
import javax.inject.Inject

class PullRequestsPresenter @Inject constructor(
        private val getPullRequestsCoordinator: GetPullRequestsCoordinator,
        private val automaticUnsubscriber: AutomaticUnsubscriber
) : PullRequestsContract.Presenter {

    override fun initializeContents(data: CallData) {
        val subscription: Subscription = Observable.just(data)
                .compose(getPullRequestsCoordinator)
                .subscribe()
        automaticUnsubscriber.add(subscription)
    }
}