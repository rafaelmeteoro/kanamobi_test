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

class ShowLoadingRepositoriesTest {

    @Mock
    lateinit var view: RepositoriesContract.View

    lateinit var impl: ShowLoadingRepositories

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                ShowLoadingRepositoriesImpl(
                        Schedulers.immediate(),
                        view
                )
        )
    }

    @Test
    fun callLoading_shouldViewLoading() {
        Observable.just(1)
                .compose(impl)
                .subscribe()

        verify(view).showLoading()
        verify(view, never()).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showErrorToast()
        verify(view, never()).showData(RepositoryData(listOf()))
    }
}