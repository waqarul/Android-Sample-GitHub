package com.wtmcodex.androidsamplegithub.core.di.module

import android.content.Context
import androidx.room.Room
import com.wtmcodex.androidsamplegithub.constants.DatabaseConstants
import com.wtmcodex.androidsamplegithub.core.data.source.local.GitHubRepositoryDatabase
import com.wtmcodex.androidsamplegithub.core.data.source.local.dao.GitHubRepositoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): GitHubRepositoryDatabase {
        return Room.databaseBuilder(
            context,
            GitHubRepositoryDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideGitHubRepositoryDao(database: GitHubRepositoryDatabase): GitHubRepositoryDao {
        return database.repositoryDao()
    }
}