package com.wtmcodex.androidsamplegithub.core.data.source.repository

import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.helpers.Result

interface GitHubRepository {
    suspend fun getRepositories(): Result<List<GitHubRepositoryModel>?>
    suspend fun isRepositoryBookMarked(id: Long): Boolean
    suspend fun getRepositoryByID(id: Long): GitHubRepositoryModel?
    suspend fun shouldBookMarRepository(gitHubRepositoryModel: GitHubRepositoryModel)
    suspend fun deleteAllRepositories()
}