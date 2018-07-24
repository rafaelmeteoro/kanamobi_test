package com.meteoro.kanamobitest.ui.repositories.presentation.coordinator

import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ConvertRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.GetRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowLoadingMoreRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowMoreData
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import rx.Observable
import javax.inject.Inject

class GetMoreRepositoriesCoordinator @Inject constructor(
        private val showLoadingMoreRepositories: ShowLoadingMoreRepositories,
        private val getRepositories: GetRepositories,
        private val convertRepositories: ConvertRepositories,
        private val showMoreData: ShowMoreData
) : Observable.Transformer<Int, RepositoryData> {

    override fun call(observable: Observable<Int>?): Observable<RepositoryData>? {
        return observable
                ?.compose(showLoadingMoreRepositories)
                ?.compose(getRepositories)
                ?.compose(convertRepositories)
                ?.compose(showMoreData)
    }
}