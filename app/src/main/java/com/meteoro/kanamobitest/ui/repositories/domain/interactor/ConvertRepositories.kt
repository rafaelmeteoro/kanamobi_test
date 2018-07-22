package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryItem
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ConvertRepositories : Observable.Transformer<RepositoryDataResponse, RepositoryData>

class ConvertRepositoriesImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        @IoScheduler private val ioScheduler: Scheduler
) : ConvertRepositories {

    override fun call(observable: Observable<RepositoryDataResponse>?): Observable<RepositoryData>? {
        return observable
                ?.subscribeOn(ioScheduler)
                ?.observeOn(uiScheduler)
                ?.flatMap(this::mapRepositories)
    }

    private fun mapRepositories(data: RepositoryDataResponse): Observable<RepositoryData> {
        return Observable.just(data)
                .map {
                    val items = it.items.map {
                        RepositoryItem(it.owner.login, it.owner.avatar_url, it.name,
                                it.description, it.stargazers_count, it.forks_count)
                    }

                    return@map RepositoryData(items)
                }
    }
}