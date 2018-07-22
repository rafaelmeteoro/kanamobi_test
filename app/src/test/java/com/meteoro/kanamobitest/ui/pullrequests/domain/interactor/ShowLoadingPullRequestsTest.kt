package com.meteoro.kanamobitest.ui.pullrequests.domain.interactor

import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsContract
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers

class ShowLoadingPullRequestsTest {

    @Mock
    lateinit var view: PullRequestsContract.View

    lateinit var impl: ShowLoadingPullRequests

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                ShowLoadingPullRequestsImpl(
                        Schedulers.immediate(),
                        view
                )
        )
    }

    @Test
    fun callLoading_shouldViewLoading() {
        Observable.just(mock(CallData::class.java))
                .compose(impl)
                .subscribe()

        verify(view).showLoading()
        verify(view, never()).showEmpty()
        verify(view, never()).showError()
        verify(view, never()).showData(PullRequestData(listOf()))
    }
}