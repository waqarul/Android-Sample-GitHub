package com.wtmcodex.androidsamplegithub

import android.app.Application
import com.wtmcodex.androidsamplegithub.core.Domain
import com.wtmcodex.androidsamplegithub.core.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GitHubApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        Domain.getInstance(this)
        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}