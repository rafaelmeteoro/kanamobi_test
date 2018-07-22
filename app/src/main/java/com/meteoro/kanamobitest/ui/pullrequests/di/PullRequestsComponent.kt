package com.meteoro.kanamobitest.ui.pullrequests.di

import com.meteoro.kanamobitest.core.di.PerActivity
import com.meteoro.kanamobitest.core.di.component.LibraryComponent
import com.meteoro.kanamobitest.ui.pullrequests.presentation.PullRequestsActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(LibraryComponent::class)],
        modules = [(PullRequestsModule::class)])
interface PullRequestsComponent {
    fun inject(activity: PullRequestsActivity)
}