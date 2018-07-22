package com.meteoro.kanamobitest.ui.repositories.presentation

import com.meteoro.kanamobitest.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import com.meteoro.kanamobitest.ui.repositories.presentation.coordinator.GetMoreRepositoriesCoordinator
import com.meteoro.kanamobitest.ui.repositories.presentation.coordinator.GetRepositoriesCoordinator
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable

class RepositoriesPresenterTest {

    @Mock
    lateinit var getRepositoriesCoordinator: GetRepositoriesCoordinator

    @Mock
    lateinit var getMoreRepositoriesCoordinator: GetMoreRepositoriesCoordinator

    @Mock
    lateinit var automaticUnsubscriber: AutomaticUnsubscriber

    lateinit var presenter: RepositoriesContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = spy(
                RepositoriesPresenter(
                        getRepositoriesCoordinator,
                        getMoreRepositoriesCoordinator,
                        automaticUnsubscriber
                )
        )
    }

    @Test
    fun initializeContents_shouldCallCoordinatorsInOrder() {
        `when`(getRepositoriesCoordinator.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryData::class.java)))

        val callOrder = inOrder(
                getRepositoriesCoordinator
        )

        presenter.initializeContents()

        callOrder.verify(getRepositoriesCoordinator).call(ArgumentMatchers.any())
    }

    @Test
    fun getMoreItem_shouldCallCoordinatorsInOrder() {
        `when`(getMoreRepositoriesCoordinator.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryData::class.java)))

        val callOrder = inOrder(
                getMoreRepositoriesCoordinator
        )

        presenter.getMoreItems(ArgumentMatchers.anyInt())

        callOrder.verify(getMoreRepositoriesCoordinator).call(ArgumentMatchers.any())
    }
}