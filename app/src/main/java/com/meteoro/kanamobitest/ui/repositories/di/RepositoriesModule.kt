package com.meteoro.kanamobitest.ui.repositories.di

import android.arch.lifecycle.LifecycleOwner
import com.meteoro.kanamobitest.core.di.PerActivity
import com.meteoro.kanamobitest.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.kanamobitest.core.lifecycle.LifecycleUnsubscriber
import com.meteoro.kanamobitest.ui.repositories.domain.ShowLoadingRepositories
import com.meteoro.kanamobitest.ui.repositories.domain.ShowLoadingRepositoriesImpl
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesActivity
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesContract
import com.meteoro.kanamobitest.ui.repositories.presentation.RepositoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule(private val activity: RepositoriesActivity) {

    @Provides
    @PerActivity
    fun view(): RepositoriesContract.View = activity

    @Provides
    @PerActivity
    fun presenter(presenter: RepositoriesPresenter): RepositoriesContract.Presenter = presenter

    @Provides
    @PerActivity
    fun lifecycleOwner(): LifecycleOwner = activity

    @Provides
    @PerActivity
    fun automaticUnsubscriber(impl: LifecycleUnsubscriber): AutomaticUnsubscriber = impl

    @Provides
    @PerActivity
    fun showLoadingRepositories(impl: ShowLoadingRepositoriesImpl): ShowLoadingRepositories = impl
}