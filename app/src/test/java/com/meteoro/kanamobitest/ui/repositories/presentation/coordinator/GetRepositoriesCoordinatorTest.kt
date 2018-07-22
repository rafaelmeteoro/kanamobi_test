package com.meteoro.kanamobitest.ui.repositories.presentation.coordinator

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ConvertRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.GetRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowData
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowLoadingRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable

class GetRepositoriesCoordinatorTest {

    @Mock
    lateinit var showLoadingRepositories: ShowLoadingRepositories

    @Mock
    lateinit var getRepositories: GetRepositories

    @Mock
    lateinit var convertRepositories: ConvertRepositories

    @Mock
    lateinit var showData: ShowData

    lateinit var impl: GetRepositoriesCoordinator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                GetRepositoriesCoordinator(
                        showLoadingRepositories,
                        getRepositories,
                        convertRepositories,
                        showData
                )
        )
    }

    @Test
    fun callGetRepositoriesCoordinator_shouldExecuteInOrder() {
        `when`(showLoadingRepositories.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(1))
        `when`(getRepositories.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryDataResponse::class.java)))
        `when`(convertRepositories.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryData::class.java)))
        `when`(showData.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryData::class.java)))

        val callOrder = inOrder(
                showLoadingRepositories,
                getRepositories,
                convertRepositories,
                showData
        )

        Observable.just(1)
                .compose(impl)
                .subscribe()

        callOrder.verify(showLoadingRepositories).call(ArgumentMatchers.any())
        callOrder.verify(getRepositories).call(ArgumentMatchers.any())
        callOrder.verify(convertRepositories).call(ArgumentMatchers.any())
        callOrder.verify(showData).call(ArgumentMatchers.any())
    }
}