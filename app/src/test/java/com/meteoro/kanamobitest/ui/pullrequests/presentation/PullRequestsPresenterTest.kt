package com.meteoro.kanamobitest.ui.pullrequests.presentation

import com.meteoro.kanamobitest.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.coordinator.GetPullRequestsCoordinator
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable

class PullRequestsPresenterTest {

    @Mock
    lateinit var getPullRequestsCoordinator: GetPullRequestsCoordinator

    @Mock
    lateinit var automaticUnsubscriber: AutomaticUnsubscriber

    lateinit var presenter: PullRequestsContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = spy(
                PullRequestsPresenter(
                        getPullRequestsCoordinator,
                        automaticUnsubscriber
                )
        )
    }

    @Test
    fun initializeContents_shouldCallCoordinatorsInOder() {
        `when`(getPullRequestsCoordinator.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(PullRequestData::class.java)))

        val callOrder = inOrder(
                getPullRequestsCoordinator
        )

        presenter.initializeContents(mock(CallData::class.java))

        callOrder.verify(getPullRequestsCoordinator).call(ArgumentMatchers.any())
    }
}