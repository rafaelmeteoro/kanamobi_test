package com.meteoro.kanamobitest.ui.pullrequests.di

import android.arch.lifecycle.LifecycleOwner
import com.meteoro.kanamobitest.core.di.PerActivity
import com.meteoro.kanamobitest.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.kanamobitest.core.lifecycle.LifecycleUnsubscriber
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsActivity
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsContract
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsPresenter
import dagger.Module
import dagger.Provides

@Module
class PullRequestsModule(private val activity: PullRequestsActivity) {

    @Provides
    @PerActivity
    fun view(): PullRequestsContract.View = activity

    @Provides
    @PerActivity
    fun presenter(presenter: PullRequestsPresenter): PullRequestsContract.Presenter = presenter

    @Provides
    @PerActivity
    fun lifecycleOwner(): LifecycleOwner = activity

    @Provides
    @PerActivity
    fun automaticUnsubscriber(impl: LifecycleUnsubscriber): AutomaticUnsubscriber = impl
}