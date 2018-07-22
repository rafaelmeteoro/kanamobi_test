package com.meteoro.kanamobitest.ui.pullrequests.presentation.coordinator

import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ConvertPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.GetPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ShowData
import com.meteoro.kanamobitest.ui.pullrequests.domain.interactor.ShowLoadingPullRequests
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable

class GetPullRequestsCoordinatorTest {

    @Mock
    lateinit var showLoadingPullRequests: ShowLoadingPullRequests

    @Mock
    lateinit var getPullRequests: GetPullRequests

    @Mock
    lateinit var convertPullRequests: ConvertPullRequests

    @Mock
    lateinit var showData: ShowData

    lateinit var impl: GetPullRequestsCoordinator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                GetPullRequestsCoordinator(
                        showLoadingPullRequests,
                        getPullRequests,
                        convertPullRequests,
                        showData
                )
        )
    }

    @Test
    fun callGetPullRequestsCoordinator_shouldExecuteInOrder() {
        `when`(showLoadingPullRequests.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(CallData::class.java)))
        `when`(getPullRequests.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(listOf()))
        `when`(convertPullRequests.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(PullRequestData::class.java)))
        `when`(showData.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(PullRequestData::class.java)))

        val callOrder = inOrder(
                showLoadingPullRequests,
                getPullRequests,
                convertPullRequests,
                showData
        )

        Observable.just(mock(CallData::class.java))
                .compose(impl)
                .subscribe()

        callOrder.verify(showLoadingPullRequests).call(ArgumentMatchers.any())
        callOrder.verify(getPullRequests).call(ArgumentMatchers.any())
        callOrder.verify(convertPullRequests).call(ArgumentMatchers.any())
        callOrder.verify(showData).call(ArgumentMatchers.any())
    }
}