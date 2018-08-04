package com.meteoro.kanamobitest.new_core.behaviours

import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import com.meteoro.kanamobitest.util.MockitoHelpers.Companion.oneTimeOnly
import org.mockito.Mockito.verify

class ShowAtStartHideWhenDoneTests {

    val targetScheduler = Schedulers.trampoline()

    @Mock
    lateinit var whenStart: Action

    @Mock
    lateinit var whenDone: Action

    @Before
    fun beforeEachTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldHideAtStart_AndNotShowError_WhenFlowEmmits() {
        Flowable.just("AB", "CD")
                .compose(ShowAtStartHideWhenDone(whenStart, whenDone, targetScheduler))
                .subscribe()

        verifyShowAndHideWithFlow()
    }

    @Test
    fun shouldHideAtStart_AndNotShowError_WhenEmptyFlow() {
        Flowable.empty<String>()
                .compose(ShowAtStartHideWhenDone(whenStart, whenDone, targetScheduler))
                .subscribe()

        verifyShowAndHideWithFlow()
    }

    @Test
    fun shouldHideAtStart_AndNotShowError_WithErrorFlow() {
        Flowable.error<String>(RuntimeException("Dam it!!!"))
                .compose(ShowAtStartHideWhenDone(whenStart, whenDone, targetScheduler))
                .subscribe({}, {}, {})

        verifyShowAndHideWithFlow()
    }

    private fun verifyShowAndHideWithFlow() {
        verify(whenStart, oneTimeOnly()).run()
        verify(whenDone, oneTimeOnly()).run()
    }
}