package com.wtmcodex.androidsamplegithub.core.di.module

import com.wtmcodex.androidsamplegithub.core.data.source.repository.GitHubRepository
import com.wtmcodex.androidsamplegithub.core.data.source.repository.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindGitHubRepository(gitHubRepositoryImpl: GitHubRepositoryImpl): GitHubRepository
}