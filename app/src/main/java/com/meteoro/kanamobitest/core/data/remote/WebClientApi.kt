package com.meteoro.kanamobitest.core.data.remote

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import retrofit2.http.GET
import rx.Observable

interface WebClientApi {
    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepositories(): Observable<RepositoryDataResponse>
}