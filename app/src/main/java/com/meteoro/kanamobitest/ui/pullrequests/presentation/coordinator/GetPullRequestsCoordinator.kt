package com.meteoro.kanamobitest.ui.pullrequests.presentation.coordinator

import com.meteoro.kanamobitest.core.data.PullRequestInfo
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.GetPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ShowLoadingPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import rx.Observable
import javax.inject.Inject

class GetPullRequestsCoordinator @Inject constructor(
        private val showLoadingPullRequests: ShowLoadingPullRequests,
        private val getPullRequests: GetPullRequests
) : Observable.Transformer<CallData, List<PullRequestInfo>> {

    override fun call(observable: Observable<CallData>?): Observable<List<PullRequestInfo>>? {
        return observable
                ?.compose(showLoadingPullRequests)
                ?.compose(getPullRequests)
    }
}