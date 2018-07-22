package com.meteoro.kanamobitest.core.data.remote

import com.meteoro.kanamobitest.core.data.PullRequestInfo
import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface WebClientApi {
    @GET("/search/repositories")
    fun getRepositories(@Query("q") language: String = "language:Java",
                        @Query("sort") sort: String = "stars",
                        @Query("page") page: Int = 1): Observable<RepositoryDataResponse>

    @GET("/repos/{user}/{repo}/pulls")
    fun getPullRequests(@Path("user") user: String,
                        @Path("repo") repo: String): Observable<List<PullRequestInfo>>
}