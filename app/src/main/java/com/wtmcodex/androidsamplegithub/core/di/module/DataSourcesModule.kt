package com.wtmcodex.androidsamplegithub.core.di.module

import com.wtmcodex.androidsamplegithub.core.data.source.local.GitHubRepositoryLocalDataSource
import com.wtmcodex.androidsamplegithub.core.data.source.local.GitHubRepositoryLocalDataSourceImpl
import com.wtmcodex.androidsamplegithub.core.data.source.remote.GitHubRepositoryRemoteDataSource
import com.wtmcodex.androidsamplegithub.core.data.source.remote.GitHubRepositoryRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourcesModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: GitHubRepositoryLocalDataSourceImpl): GitHubRepositoryLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: GitHubRepositoryRemoteDataSourceImpl): GitHubRepositoryRemoteDataSource
}