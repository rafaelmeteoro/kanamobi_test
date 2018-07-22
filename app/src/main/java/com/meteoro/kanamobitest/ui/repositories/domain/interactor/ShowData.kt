package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

interface ShowData : Observable.Transformer<RepositoryData, RepositoryData>

class ShowDataImpl @Inject constructor(
        @UiScheduler private val uiScheduler: Scheduler,
        private val view: RepositoriesContract.View
) : ShowData {

    override fun call(observable: Observable<RepositoryData>?): Observable<RepositoryData>? {
        return observable
                ?.subscribeOn(uiScheduler)
                ?.observeOn(uiScheduler)
                ?.doOnNext(this::showData)
                ?.doOnError(this::showError)
                ?.onErrorResumeNext(Observable.empty())
    }

    private fun showData(data: RepositoryData) {
        if (data.repositories.isEmpty()) {
            view.showEmpty()
        } else {
            view.showData(data)
        }
    }

    private fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        view.showError()
    }
}