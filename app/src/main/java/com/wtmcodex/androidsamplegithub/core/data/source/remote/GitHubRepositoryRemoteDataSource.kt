package com.wtmcodex.androidsamplegithub.core.data.source.remote

import com.wtmcodex.androidsamplegithub.core.data.model.backend.GitHubRepositoryModelNetwork
import com.wtmcodex.androidsamplegithub.helpers.Result

interface GitHubRepositoryRemoteDataSource {
    suspend fun getRepositories(): Result<List<GitHubRepositoryModelNetwork>>
}