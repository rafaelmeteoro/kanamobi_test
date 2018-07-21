package com.meteoro.kanamobitest.core.data.remote

import com.meteoro.kanamobitest.core.data.RepositoryDataResponse
import rx.Observable

interface RepositoryApi {
    fun getRepositories(): Observable<RepositoryDataResponse>
}