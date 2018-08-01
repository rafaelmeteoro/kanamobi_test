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

class ShowMoreDataTest {

    @Mock
    lateinit var view: RepositoriesContract.View

    lateinit var impl: ShowMoreData

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                ShowMoreDataImpl(
                        Schedulers.immediate(),
                        view
                )
        )
    }

    @Test
    fun success_shuldShowData() {
        val data = RepositoryData(listOf())

        Observable.just(data)
                .compose(impl)
                .subscribe()

        verify(view).showData(data)
        verify(view, never()).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showErrorToast()
        verify(view, never()).showLoading()
    }
}