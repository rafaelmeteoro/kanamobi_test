package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import com.meteoro.kanamobitest.core.data.remote.RepositoryApi
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface GetRepositories : Observable.Transformer<Int, RepositoryDataResponse>

class GetRepositoriesImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        @IoScheduler private val ioScheduler: Scheduler,
        private val repositoryApi: RepositoryApi
) : GetRepositories {

    override fun call(observable: Observable<Int>?): Observable<RepositoryDataResponse>? {
        return observable
                ?.flatMap(this::getRepositories)
    }

    private fun getRepositories(page: Int): Observable<RepositoryDataResponse> {
        return repositoryApi.getRepositories(page)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
    }
}