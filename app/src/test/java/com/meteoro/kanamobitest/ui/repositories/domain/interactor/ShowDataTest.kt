package com.meteoro.kanamobitest.ui.repositories.domain.interactor

import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryItem
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers

class ShowDataTest {

    @Mock
    lateinit var view: RepositoriesContract.View

    lateinit var impl: ShowData

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                ShowDataImpl(
                        Schedulers.immediate(),
                        view
                )
        )
    }

    @Test
    fun success_shouldShowData() {
        val data = RepositoryData(
                arrayListOf(
                        RepositoryItem("", "", "", "", 0, 0)
                )
        )

        Observable.just(data)
                .compose(impl)
                .subscribe()

        verify(view).showData(data)
        verify(view, never()).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showErrorToast()
        verify(view, never()).showLoading()
    }

    @Test
    fun success_shouldShowEmpty() {
        val data = RepositoryData(arrayListOf())

        Observable.just(data)
                .compose(impl)
                .subscribe()

        verify(view).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showErrorToast()
        verify(view, never()).showLoading()
        verify(view, never()).showData(data)
    }
}