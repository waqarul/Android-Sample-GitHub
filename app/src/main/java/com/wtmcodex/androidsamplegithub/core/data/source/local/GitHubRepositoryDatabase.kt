package com.wtmcodex.androidsamplegithub.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wtmcodex.androidsamplegithub.core.data.source.local.dao.GitHubRepositoryDao
import com.wtmcodex.androidsamplegithub.core.data.source.local.entity.DBGitHubRepository

@Database(entities = [DBGitHubRepository::class], version = 1, exportSchema = false)
abstract class GitHubRepositoryDatabase : RoomDatabase() {
    abstract fun repositoryDao(): GitHubRepositoryDao
}