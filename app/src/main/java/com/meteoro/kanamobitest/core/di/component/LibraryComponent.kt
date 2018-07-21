package com.meteoro.kanamobitest.core.di.component

import com.meteoro.kanamobitest.core.di.module.ApplicationModule
import com.meteoro.kanamobitest.core.di.module.LibraryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LibraryModule::class, ApplicationModule::class))
interface LibraryComponent