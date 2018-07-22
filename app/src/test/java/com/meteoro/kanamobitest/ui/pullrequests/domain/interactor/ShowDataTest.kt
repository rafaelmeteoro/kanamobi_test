package com.meteoro.kanamobitest.ui.pullrequests.domain.interactor

import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestItem
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers

class ShowDataTest {

    @Mock
    lateinit var view: PullRequestsContract.View

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
        val data = PullRequestData(
                arrayListOf(
                        PullRequestItem("", "", "", "", "", "")
                )
        )

        Observable.just(data)
                .compose(impl)
                .subscribe()

        verify(view).showData(data)
        verify(view, never()).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showLoading()
    }

    @Test
    fun success_shouldShowEmpty() {
        val data = PullRequestData(arrayListOf())

        Observable.just(data)
                .compose(impl)
                .subscribe()

        verify(view).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showLoading()
        verify(view, never()).showData(data)
    }
}