package com.meteoro.kanamobitest.core.data.remote

import com.meteoro.kanamobitest.core.data.PullRequestInfo
import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import rx.Observable

interface RepositoryApi {
    fun getRepositories(page: Int): Observable<RepositoryDataResponse>

    fun getPullRequests(user: String, repo: String): Observable<List<PullRequestInfo>>
}