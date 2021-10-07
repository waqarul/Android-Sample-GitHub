package com.wtmcodex.androidsamplegithub.core.data.source.local

import com.wtmcodex.androidsamplegithub.core.data.source.local.entity.DBGitHubRepository

interface GitHubRepositoryLocalDataSource {
    suspend fun insertAll(items: List<DBGitHubRepository>)
    suspend fun insertOrUpdate(repository: DBGitHubRepository)
    suspend fun getAllRepositories(): List<DBGitHubRepository>?
    suspend fun getRepositoryByID(id: Long): DBGitHubRepository?
    suspend fun deleteAllRepositories()
}