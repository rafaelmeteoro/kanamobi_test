package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ShowLoadingMoreRepositories : Observable.Transformer<Int, Int>

class ShowLoadingMoreRepositoriesImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        private val view: RepositoriesContract.View
) : ShowLoadingMoreRepositories {

    override fun call(observable: Observable<Int>?): Observable<Int>? {
        return observable
                ?.subscribeOn(uiScheduler)
                ?.observeOn(uiScheduler)
                ?.doOnNext { this.showLoading() }
    }

    private fun showLoading() {
        view.showLoadingMore()
    }
}