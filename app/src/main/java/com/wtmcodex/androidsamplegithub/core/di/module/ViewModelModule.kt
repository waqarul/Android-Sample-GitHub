package com.wtmcodex.androidsamplegithub.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wtmcodex.androidsamplegithub.ViewModelFactory
import com.wtmcodex.androidsamplegithub.core.di.key.ViewModelKey
import com.wtmcodex.androidsamplegithub.features.home.HomeViewModel
import com.wtmcodex.androidsamplegithub.features.repositorydetail.GitHubRepositoryDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(GitHubRepositoryDetailViewModel::class)
    abstract fun bindGitHubRepositoryDetailViewModel(viewModel: GitHubRepositoryDetailViewModel): ViewModel
}