package com.meteoro.kanamobitest.core.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientUtil(private val builder: Builder) {

    fun <T> create(type: Class<T>): T {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (builder.enableLogging()) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        clientBuilder.connectTimeout(builder.connectTimeout(), TimeUnit.SECONDS)
                .readTimeout(builder.readTimeout(), TimeUnit.SECONDS)
                .writeTimeout(builder.writeTimeout(), TimeUnit.SECONDS)

        val client: OkHttpClient = clientBuilder.build()
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(builder.baseUrl())
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        return retrofit.create(type)
    }

    class Builder(private var baseUrl: String = "",
                  private var enableLogging: Boolean = false,
                  private var readTimeout: Long = 30,
                  private var writeTimeout: Long = 30,
                  private var connectTimeout: Long = 30) {

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun enableLogging(enableLogging: Boolean) = apply { this.enableLogging = enableLogging }

        fun baseUrl(): String = baseUrl
        fun enableLogging(): Boolean = enableLogging
        fun readTimeout(): Long = readTimeout
        fun writeTimeout(): Long = writeTimeout
        fun connectTimeout(): Long = connectTimeout

        fun build() = ApiClientUtil(this)
    }
}