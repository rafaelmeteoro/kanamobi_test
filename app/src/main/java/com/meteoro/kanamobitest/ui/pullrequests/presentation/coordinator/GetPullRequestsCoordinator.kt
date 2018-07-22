package com.meteoro.kanamobitest.ui.pullrequests.presentation.coordinator

import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ConvertPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.GetPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ShowData
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ShowLoadingPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import rx.Observable
import javax.inject.Inject

class GetPullRequestsCoordinator @Inject constructor(
        private val showLoadingPullRequests: ShowLoadingPullRequests,
        private val getPullRequests: GetPullRequests,
        private val convertPullRequests: ConvertPullRequests,
        private val showData: ShowData
) : Observable.Transformer<CallData, PullRequestData> {

    override fun call(observable: Observable<CallData>?): Observable<PullRequestData>? {
        return observable
                ?.compose(showLoadingPullRequests)
                ?.compose(getPullRequests)
                ?.compose(convertPullRequests)
                ?.compose(showData)
    }
}