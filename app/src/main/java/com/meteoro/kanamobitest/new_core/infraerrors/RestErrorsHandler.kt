package com.meteoro.kanamobitest.new_core.infraerrors

import com.meteoro.kanamobitest.new_core.behaviours.erros.ContentNotFoundError
import com.meteoro.kanamobitest.new_core.behaviours.erros.UnexpectedResponseError
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import org.reactivestreams.Publisher
import retrofit2.HttpException

class RestErrorsHandler<T> : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.onErrorResumeNext(this::handleIfRestError)
    }

    private fun handleIfRestError(throwable: Throwable): Publisher<T> {
        if (otherThanNotFount(throwable)) {
            return Flowable.error(UnexpectedResponseError("Undesired response for this call"))
        }

        if (notFound(throwable)) {
            return Flowable.error(ContentNotFoundError())
        }

        return Flowable.error(throwable)
    }

    private fun otherThanNotFount(throwable: Throwable): Boolean {
        return throwable is HttpException && !notFound(throwable)
    }

    private fun notFound(throwable: Throwable): Boolean {
        if (throwable is HttpException) {
            return throwable.code() == 404
        }

        return false
    }
}