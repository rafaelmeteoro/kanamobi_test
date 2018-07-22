package com.meteoro.kanamobitest.core.data.remote

import com.meteoro.kanamobitest.core.client.ApiClientUtil
import com.meteoro.kanamobitest.core.data.PullRequestInfo
import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import rx.Observable

class RepositoryApiImpl(host: String) : RepositoryApi {

    private val api: WebClientApi = ApiClientUtil.Builder()
            .enableLogging(true)
            .baseUrl(host)
            .build()
            .create(WebClientApi::class.java)

    override fun getRepositories(page: Int): Observable<RepositoryDataResponse> {
        return api.getRepositories(page = page)
    }

    override fun getPullRequests(user: String, repo: String): Observable<List<PullRequestInfo>> {
        return api.getPullRequests(user, repo)
    }
}