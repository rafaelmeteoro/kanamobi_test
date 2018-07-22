package com.meteoro.kanamobitest.ui.repositories.presentation.coordinator

import com.meteoro.kanamobitest.ui.repositories.domain.ShowLoadingRepositories
import rx.Observable
import javax.inject.Inject

class GetRepositoriesCoordinator @Inject constructor(
        private val showLoadingRepositories: ShowLoadingRepositories
): Observable.Transformer<String, String> {

    override fun call(observable: Observable<String>?): Observable<String>? {
        return observable?.compose(showLoadingRepositories)
    }
}