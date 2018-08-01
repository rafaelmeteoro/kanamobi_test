package com.meteoro.kanamobitest.core.infraerrors

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.meteoro.kanamobitest.core.behaviours.errors.UnexpectedResponseError
import io.reactivex.Flowable
import org.junit.Test

class DeserializationIssuesHandlerTests {

    private val handler: DeserializationIssuesHandler<String> = DeserializationIssuesHandler()

    @Test
    fun shouldHandle_GsonThrowsIllegalStateException() {
        val broken: Flowable<String> = Flowable.error(IllegalStateException("Some message"))

        broken.compose(handler)
                .test()
                .assertError(this::checkHandleAsDeserializationError)
    }

    @Test
    fun shouldHandle_GsonThrowsIOException() {
        val broken: Flowable<String> = Flowable.error(JsonIOException("Cannot read file"))

        broken.compose(handler)
                .test()
                .assertError(this::checkHandleAsDeserializationError)
    }

    @Test
    fun shouldHandle_GsonThrowsSintaxException() {
        val broken: Flowable<String> = Flowable.error(JsonSyntaxException("Json should not have comments"))

        broken.compose(handler)
                .test()
                .assertError(this::checkHandleAsDeserializationError)
    }

    @Test
    fun shouldHandle_GsonThrowsParseException() {
        val broken: Flowable<String> = Flowable.error(JsonParseException("Failed to parse object"))

        broken.compose(handler)
                .test()
                .assertError(this::checkHandleAsDeserializationError)
    }

    @Test
    fun shouldNotHandle_OtherErros() {
        val fuck = IllegalAccessError("FUCK")
        val broken: Flowable<String> = Flowable.error(fuck)

        broken.compose(handler)
                .test()
                .assertError { it -> it.equals(fuck) }
    }

    private fun checkHandleAsDeserializationError(throwable: Throwable): Boolean {
        if (throwable is UnexpectedResponseError) {
            return throwable.message.contentEquals("Deserialization Error")
        }

        return false
    }
}