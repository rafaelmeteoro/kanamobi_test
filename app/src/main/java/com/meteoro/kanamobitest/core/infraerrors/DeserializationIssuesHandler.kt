package com.meteoro.kanamobitest.core.infraerrors

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.meteoro.kanamobitest.core.behaviours.errors.UnexpectedResponseError
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import org.reactivestreams.Publisher

class DeserializationIssuesHandler<T> : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.onErrorResumeNext(this::handleErrorFromDeserializer)
    }

    private fun handleErrorFromDeserializer(throwable: Throwable): Publisher<T> {
        if (isDeserializationError(throwable)) {
            return Flowable.error(UnexpectedResponseError("Deserialization Error"))
        }

        return Flowable.error(throwable)
    }

    private fun isDeserializationError(throwable: Throwable): Boolean {
        return throwable is IllegalStateException
                || throwable is JsonIOException
                || throwable is JsonSyntaxException
                || throwable is JsonParseException
    }
}