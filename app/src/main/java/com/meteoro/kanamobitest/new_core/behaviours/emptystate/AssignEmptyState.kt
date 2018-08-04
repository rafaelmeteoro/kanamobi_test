package com.meteoro.kanamobitest.new_core.behaviours.emptystate

import com.meteoro.kanamobitest.new_core.behaviours.HideAtStartShowAtError
import com.meteoro.kanamobitest.new_core.behaviours.erros.ContentNotFoundError
import com.meteoro.kanamobitest.util.ErrorPredicate
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import org.reactivestreams.Publisher

class AssignEmptyState<T>(
        private val view: EmptyStateView,
        private val uiScheduler: Scheduler
) : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {

        val predicate = object : ErrorPredicate {
            override fun evaluate(error: Throwable): Boolean {
                return error is ContentNotFoundError
            }
        }

        val delegate = HideAtStartShowAtError<T>(
                view.hideEmptyState(),
                view.showEmptyState(),
                predicate,
                uiScheduler
        )

        return upstream.compose(delegate)
    }
}