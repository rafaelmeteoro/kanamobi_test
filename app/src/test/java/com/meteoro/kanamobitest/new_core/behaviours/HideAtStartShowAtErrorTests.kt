package com.meteoro.kanamobitest.new_core.behaviours

import com.meteoro.kanamobitest.new_core.behaviours.HideAtStartShowAtError
import com.meteoro.kanamobitest.util.ErrorPredicate
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import com.meteoro.kanamobitest.util.MockitoHelpers.Companion.oneTimeOnly
import io.reactivex.functions.Consumer
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions

class HideAtStartShowAtErrorTests {

    private val scheduler = Schedulers.trampoline()

    private val positive = object : ErrorPredicate {
        override fun evaluate(error: Throwable): Boolean {
            return true
        }
    }

    private val negative = object : ErrorPredicate {
        override fun evaluate(error: Throwable): Boolean {
            return false
        }
    }

    @Mock
    lateinit var whenStart: Action

    @Mock
    lateinit var atError: Action

    @Before
    fun beforeEachTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Throws(Exception::class)
    @Test
    fun shouldHideAtStart_AndNOtShowError_WhenFlowEmmits() {
        Flowable.just("A", "B", "C")
                .compose(HideAtStartShowAtError(whenStart, atError, positive, scheduler))
                .subscribe()

        verify(whenStart, oneTimeOnly()).run()
        verifyZeroInteractions(atError)
    }

    @Throws(Exception::class)
    @Test
    fun shouldHideAtStart_AndNotShowError_WhenFlowIsEmpty() {
        Flowable.empty<String>()
                .compose(HideAtStartShowAtError(whenStart, atError, positive, scheduler))
                .subscribe()

        verify(whenStart, oneTimeOnly()).run()
        verifyZeroInteractions(atError)
    }

    @Throws(Exception::class)
    @Test
    fun shouldHideAtStart_AndNotShowError_WhenEvaluationFails() {
        Flowable.error<RuntimeException>(RuntimeException("Ouch"))
                .compose(HideAtStartShowAtError(whenStart, atError, negative, scheduler))
                .subscribe({}, {}, {})

        verify(whenStart, oneTimeOnly()).run()
        verifyZeroInteractions(atError)
    }

    @Throws(Exception::class)
    @Test
    fun shouldHideAtStart_AndShowError_WhenEvaluationSuccessful() {
        Flowable.error<RuntimeException>(RuntimeException("Ouch Again"))
                .compose(HideAtStartShowAtError(whenStart, atError, positive, scheduler))
                .subscribe({}, {}, {})

        verify(whenStart, oneTimeOnly()).run()
        verify(atError, oneTimeOnly()).run()
    }
}