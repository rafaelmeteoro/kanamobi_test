package com.meteoro.kanamobitest.core.data.remote

import com.meteoro.kanamobitest.core.client.ApiClientUtil
import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import rx.Observable

class RepositoryApiImpl(host: String) : RepositoryApi {

    private val api: WebClientApi = ApiClientUtil.Builder()
            .enableLogging(true)
            .baseUrl(host)
            .build()
            .create(WebClientApi::class.java)

    override fun getRepositories(): Observable<RepositoryDataResponse> {
        return api.getRepositories()
    }
}