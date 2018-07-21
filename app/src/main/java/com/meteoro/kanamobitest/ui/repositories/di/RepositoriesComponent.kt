package com.meteoro.kanamobitest.ui.repositories.di

import com.meteoro.kanamobitest.core.di.PerActivity
import com.meteoro.kanamobitest.core.di.component.LibraryComponent
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(LibraryComponent::class),
        modules = arrayOf(RepositoriesModule::class))
interface RepositoriesComponent {
    fun inject(activity: RepositoriesActivity)
}