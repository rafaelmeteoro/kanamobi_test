package com.meteoro.kanamobitest.ui.pullrequests.domain.interactor

import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsContract
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ShowLoadingPullRequests : Observable.Transformer<CallData, CallData>

class ShowLoadingPullRequestsImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        private val view: PullRequestsContract.View
) : ShowLoadingPullRequests {

    override fun call(observable: Observable<CallData>?): Observable<CallData>? {
        return observable
                ?.subscribeOn(uiScheduler)
                ?.observeOn(uiScheduler)
                ?.doOnNext { this.showLoading() }
    }

    private fun showLoading() {
        view.showLoading()
    }
}