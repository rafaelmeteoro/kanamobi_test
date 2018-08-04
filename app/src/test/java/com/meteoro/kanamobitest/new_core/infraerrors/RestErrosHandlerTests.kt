package com.meteoro.kanamobitest.new_core.infraerrors

import com.meteoro.kanamobitest.new_core.behaviours.erros.ContentNotFoundError
import com.meteoro.kanamobitest.new_core.behaviours.erros.UnexpectedResponseError
import io.reactivex.Flowable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RestErrosHandlerTests {

    private val handler: RestErrorsHandler<String> = RestErrorsHandler()

    @Test
    fun shouldHandle_404() {
        val body = ResponseBody.create(
                MediaType.parse("application/json"),
                "{\"message\":\"Not found\"}")

        val error: Response<String> = Response.error(404, body)
        val exp = HttpException(error)

        val notFound: Flowable<String> = Flowable.error(exp)

        notFound.compose(handler)
                .test()
                .assertError(ContentNotFoundError::class.java)
    }

    @Test
    fun shouldHandle_5xx() {
        val body = ResponseBody.create(
                MediaType.parse("application/json"),
                "{\"message\":\"Internal server error\"}")

        val error: Response<String> = Response.error(503, body)
        val exp = HttpException(error)

        val internalServer: Flowable<String> = Flowable.error(exp)

        internalServer.compose(handler)
                .test()
                .assertError(UnexpectedResponseError::class.java)
    }

    @Test
    fun shouldNotHandle_OtherErrors() {
        val npe = NullPointerException()
        val otherIssue: Flowable<String> = Flowable.error(npe)

        otherIssue.compose(handler)
                .test()
                .assertError { it -> it.equals(npe) }
    }
}