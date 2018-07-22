package com.meteoro.kanamobitest.core.di.component

import com.meteoro.kanamobitest.core.di.module.ApplicationModule
import com.meteoro.kanamobitest.core.di.module.LibraryModule
import com.meteoro.kanamobitest.core.di.qualifers.UiScheduler
import dagger.Component
import rx.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LibraryModule::class, ApplicationModule::class))
interface LibraryComponent {

    @UiScheduler
    fun uiScheduler(): Scheduler
}