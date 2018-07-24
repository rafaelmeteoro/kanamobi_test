package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers

class ShowLoadingMoreRepositoriesTest {

    @Mock
    lateinit var view: RepositoriesContract.View

    lateinit var impl: ShowLoadingMoreRepositories

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                ShowLoadingMoreRepositoriesImpl(
                        Schedulers.immediate(),
                        view
                )
        )
    }

    @Test
    fun callLoading_shouldViewLoadingMore() {
        Observable.just(1)
                .compose(impl)
                .subscribe()

        verify(view).showLoadingMore()
        verify(view, never()).showLoading()
        verify(view, never()).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showErrorToast()
        verify(view, never()).showData(RepositoryData(listOf()))
    }
}