package com.meteoro.kanamobitest.core.di.module

import android.content.Context
import com.meteoro.kanamobitest.application.MyApplication
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: MyApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    @UiScheduler
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @IoScheduler
    fun provideJobScheduer(): Scheduler = Schedulers.io()
}