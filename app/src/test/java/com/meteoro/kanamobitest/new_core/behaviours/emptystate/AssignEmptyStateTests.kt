package com.meteoro.kanamobitest.new_core.behaviours.emptystate

import com.meteoro.kanamobitest.new_core.behaviours.erros.ContentNotFoundError
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import com.meteoro.kanamobitest.util.MockitoHelpers.Companion.oneTimeOnly
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

class AssignEmptyStateTests {

    private val uiScheduler = Schedulers.trampoline()
    lateinit var assignEmptyness: AssignEmptyState<Int>

    @Mock
    lateinit var showEmptyState: Action

    @Mock
    lateinit var hideEmptyState: Action

    @Before
    fun beforeEachTest() {
        MockitoAnnotations.initMocks(this)

        val view = object : EmptyStateView {
            override fun showEmptyState(): Action {
                return showEmptyState
            }

            override fun hideEmptyState(): Action {
                return hideEmptyState
            }
        }

        assignEmptyness = AssignEmptyState(view, uiScheduler)
    }

    @Test
    fun shouldNotAssignEmpty_WhenFlowEmmits() {
        Flowable.just(10, 20, 30)
                .compose(assignEmptyness)
                .subscribe()

        verify(hideEmptyState, oneTimeOnly()).run()
        verify(showEmptyState, never()).run()
    }

    @Test
    fun shouldNotAssignEmpty_WithEmptyFlow() {
        Flowable.empty<Int>()
                .compose(assignEmptyness)
                .subscribe()

        verify(hideEmptyState, oneTimeOnly()).run()
        verify(showEmptyState, never()).run()
    }

    @Test
    fun shouldNotAssignEmpty_WithNoTargetError() {
        Flowable.error<Int>(RuntimeException("WTF!!!"))
                .compose(assignEmptyness)
                .subscribe({}, {}, {})

        verify(hideEmptyState, oneTimeOnly()).run()
        verify(showEmptyState, never()).run()
    }

    @Test
    fun shouldAssignEmpty_WithNoContentError() {
        Flowable.error<Int>(ContentNotFoundError())
                .compose(assignEmptyness)
                .subscribe({}, {}, {})

        verify(hideEmptyState, oneTimeOnly()).run()
        verify(showEmptyState, oneTimeOnly()).run()
    }
}