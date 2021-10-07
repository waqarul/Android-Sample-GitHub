package com.wtmcodex.androidsamplegithub.core.di.module

import com.wtmcodex.androidsamplegithub.core.di.scope.PerActivity
import com.wtmcodex.androidsamplegithub.features.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}