package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import com.meteoro.kanamobitest.core.data.remote.RepositoryApi
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface GetRepositories : Observable.Transformer<String, RepositoryDataResponse>

class GetRepositoriesImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        @IoScheduler private val ioScheduler: Scheduler,
        private val repositoryApi: RepositoryApi
) : GetRepositories {

    override fun call(observable: Observable<String>?): Observable<RepositoryDataResponse>? {
        return observable
                ?.flatMap(this::getRepositories)
    }

    private fun getRepositories(ignore: String): Observable<RepositoryDataResponse> {
        return repositoryApi.getRepositories()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
    }
}