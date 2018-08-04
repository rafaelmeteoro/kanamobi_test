package com.meteoro.kanamobitest.new_core.lifecycles

import io.reactivex.Flowable
import org.assertj.core.api.Assertions
import org.junit.Test

class DisposeStrategyTests {

    @Test
    fun shouldDispose_OnDestroy() {
        val strategy = DisposeStrategy()
        val target = Flowable.just("A", "B", "C").subscribe()
        strategy.addDisposable(target)
        strategy.onDestroy()
        Assertions.assertThat(target.isDisposed).isTrue()
    }
}