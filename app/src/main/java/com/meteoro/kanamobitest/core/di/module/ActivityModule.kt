package com.meteoro.kanamobitest.core.di.module

import android.support.v7.app.AppCompatActivity
import com.meteoro.kanamobitest.core.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun activity(): AppCompatActivity = activity
}