package com.meteoro.kanamobitest.application

import android.app.Application
import com.meteoro.kanamobitest.core.di.component.DaggerLibraryComponent
import com.meteoro.kanamobitest.core.di.component.LibraryComponent
import com.meteoro.kanamobitest.core.di.module.ApplicationModule
import com.meteoro.kanamobitest.core.di.module.LibraryModule

class MyApplication : Application() {

    companion object {
        lateinit var component: LibraryComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerLibraryComponent.builder()
                .applicationModule(ApplicationModule(this))
                .libraryModule(LibraryModule(this))
                .build()
    }
}