package com.meteoro.kanamobitest.ui.pullrequests.domain.interactor

import com.meteoro.kanamobitest.core.data.PullRequestInfo
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestItem
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ConvertPullRequests : Observable.Transformer<List<PullRequestInfo>, PullRequestData>

class ConvertPullRequestsImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        @IoScheduler private val ioScheduler: Scheduler
) : ConvertPullRequests {

    override fun call(observable: Observable<List<PullRequestInfo>>?): Observable<PullRequestData>? {
        return observable
                ?.subscribeOn(ioScheduler)
                ?.observeOn(uiScheduler)
                ?.flatMap(this::mapPullRequests)
    }

    private fun mapPullRequests(data: List<PullRequestInfo>): Observable<PullRequestData> {
        return Observable.just(data)
                .map {
                    val items = it.map {
                        PullRequestItem(it.user.login, it.user.avatar_url, it.title,
                                it.body, it.created_at, it.html_url)
                    }

                    return@map PullRequestData(items)
                }
    }
}