package com.wtmcodex.androidsamplegithub.core.di.module

import com.wtmcodex.androidsamplegithub.features.home.HomeFragment
import com.wtmcodex.androidsamplegithub.features.repositorydetail.GitHubRepositoryDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeGitHubRepositoryDetailFragment(): GitHubRepositoryDetailFragment
}