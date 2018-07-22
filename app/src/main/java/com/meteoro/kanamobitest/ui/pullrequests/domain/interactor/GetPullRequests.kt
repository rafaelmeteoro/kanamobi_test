package com.meteoro.kanamobitest.ui.pullrequests.domain.interactor

import com.meteoro.kanamobitest.core.data.PullRequestInfo
import com.meteoro.kanamobitest.core.data.remote.RepositoryApi
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface GetPullRequests : Observable.Transformer<CallData, List<PullRequestInfo>>

class GetPullRequestsImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        @IoScheduler private val ioScheduler: Scheduler,
        private val repositoryApi: RepositoryApi
) : GetPullRequests {

    override fun call(observable: Observable<CallData>?): Observable<List<PullRequestInfo>>? {
        return observable
                ?.flatMap(this::getPullRequests)
    }

    private fun getPullRequests(data: CallData): Observable<List<PullRequestInfo>> {
        return repositoryApi.getPullRequests(data.user, data.repo)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
    }
}