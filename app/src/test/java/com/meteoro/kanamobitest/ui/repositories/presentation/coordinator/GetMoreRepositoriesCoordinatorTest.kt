package com.meteoro.kanamobitest.ui.repositories.presentation.coordinator

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ConvertRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.GetRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowLoadingMoreRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.interactor.ShowMoreData
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable

class GetMoreRepositoriesCoordinatorTest {

    @Mock
    lateinit var loadingMoreRepositories: ShowLoadingMoreRepositories

    @Mock
    lateinit var getRepositories: GetRepositories

    @Mock
    lateinit var convertRepositories: ConvertRepositories

    @Mock
    lateinit var showMoreData: ShowMoreData

    lateinit var impl: GetMoreRepositoriesCoordinator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        impl = spy(
                GetMoreRepositoriesCoordinator(
                        loadingMoreRepositories,
                        getRepositories,
                        convertRepositories,
                        showMoreData
                )
        )
    }

    @Test
    fun callGetMoreRepositoriesCoordinator_shouldExecuteInOrder() {
        `when`(loadingMoreRepositories.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(1))
        `when`(getRepositories.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryDataResponse::class.java)))
        `when`(convertRepositories.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryData::class.java)))
        `when`(showMoreData.call(ArgumentMatchers.any()))
                .thenReturn(Observable.just(mock(RepositoryData::class.java)))

        val callOrder = inOrder(
                loadingMoreRepositories,
                getRepositories,
                convertRepositories,
                showMoreData
        )

        Observable.just(1)
                .compose(impl)
                .subscribe()

        callOrder.verify(loadingMoreRepositories).call(ArgumentMatchers.any())
        callOrder.verify(getRepositories).call(ArgumentMatchers.any())
        callOrder.verify(convertRepositories).call(ArgumentMatchers.any())
        callOrder.verify(showMoreData).call(ArgumentMatchers.any())
    }
}