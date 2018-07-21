package com.meteoro.kanamobitest.core.di.component

import com.meteoro.kanamobitest.core.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent