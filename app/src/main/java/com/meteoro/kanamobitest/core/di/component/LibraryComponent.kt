package com.meteoro.kanamobitest.core.di.component

import com.meteoro.kanamobitest.core.data.remote.RepositoryApi
import com.meteoro.kanamobitest.core.di.module.ApplicationModule
import com.meteoro.kanamobitest.core.di.module.LibraryModule
import com.meteoro.kanamobitest.core.di.qualifers.IoScheduler
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import dagger.Component
import rx.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = [(LibraryModule::class), (ApplicationModule::class)])
interface LibraryComponent {

    @UiScheduler
    fun uiScheduler(): Scheduler

    @IoScheduler
    fun ioScheduler(): Scheduler

    fun repositoryApi(): RepositoryApi
}