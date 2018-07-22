package com.meteoro.kanamobitest.ui.repositories.presentation.coordinator

import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ConvertRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.GetRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowData
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowLoadingRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import rx.Observable
import javax.inject.Inject

class GetRepositoriesCoordinator @Inject constructor(
        private val showLoadingRepositories: ShowLoadingRepositories,
        private val getRepositories: GetRepositories,
        private val convertRepositories: ConvertRepositories,
        private val showData: ShowData
) : Observable.Transformer<String, RepositoryData> {

    override fun call(observable: Observable<String>?): Observable<RepositoryData>? {
        return observable
                ?.compose(showLoadingRepositories)
                ?.compose(getRepositories)
                ?.compose(convertRepositories)
                ?.compose(showData)
    }
}