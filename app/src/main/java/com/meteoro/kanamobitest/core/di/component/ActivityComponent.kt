package com.meteoro.kanamobitest.core.di.component

import com.meteoro.kanamobitest.core.di.PerActivity
import com.meteoro.kanamobitest.core.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)],
        modules = [(ActivityModule::class)])
interface ActivityComponent