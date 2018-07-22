package com.meteoro.kanamobitest.ui.repositories.presentation.coordinator

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import com.meteoro.kanamobitest.ui.repositories.domain.GetRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.ShowLoadingRepositories
import rx.Observable
import javax.inject.Inject

class GetRepositoriesCoordinator @Inject constructor(
        private val showLoadingRepositories: ShowLoadingRepositories,
        private val getRepositories: GetRepositories
) : Observable.Transformer<String, RepositoryDataResponse> {

    override fun call(observable: Observable<String>?): Observable<RepositoryDataResponse>? {
        return observable
                ?.compose(showLoadingRepositories)
                ?.compose(getRepositories)
    }
}