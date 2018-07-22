package com.meteoro.kanamobitest.ui.pullrequests.domain.interactor

import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ShowData : Observable.Transformer<PullRequestData, PullRequestData>

class ShowDataImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        private val view: PullRequestsContract.View
) : ShowData {

    override fun call(observable: Observable<PullRequestData>?): Observable<PullRequestData>? {
        return observable
                ?.subscribeOn(uiScheduler)
                ?.observeOn(uiScheduler)
                ?.doOnNext(this::showData)
                ?.doOnError(this::showError)
                ?.onErrorResumeNext(Observable.empty())
    }

    private fun showData(data: PullRequestData) {
        if (data.pullRequests.isEmpty()) {
            view.showEmpty()
        } else {
            view.showData(data)
        }
    }

    private fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        view.showError()
    }
}