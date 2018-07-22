package com.meteoro.kanamobitest.core.di.module

import android.content.Context
import com.meteoro.kanamobitest.R
import com.meteoro.kanamobitest.core.data.remote.RepositoryApi
import com.meteoro.kanamobitest.core.data.remote.RepositoryApiImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LibraryModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideRepositoryApi(): RepositoryApi {
        val host = context.getString(R.string.host_api)
        return RepositoryApiImpl(host)
    }
}