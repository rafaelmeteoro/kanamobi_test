package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ShowLoadingRepositories : Observable.Transformer<Int, Int>

class ShowLoadingRepositoriesImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        private val view: RepositoriesContract.View
) : ShowLoadingRepositories {

    override fun call(observable: Observable<Int>?): Observable<Int>? {
        return observable
                ?.subscribeOn(uiScheduler)
                ?.observeOn(uiScheduler)
                ?.doOnNext { this.showLoading() }
    }

    private fun showLoading() {
        view.showLoading()
    }
}