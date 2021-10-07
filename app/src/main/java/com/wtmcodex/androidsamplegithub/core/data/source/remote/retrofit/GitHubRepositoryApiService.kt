package com.wtmcodex.androidsamplegithub.core.data.source.remote.retrofit

import com.wtmcodex.androidsamplegithub.constants.APIConstants
import com.wtmcodex.androidsamplegithub.core.data.model.backend.GitHubRepositoryModelNetwork
import retrofit2.Response
import retrofit2.http.GET

interface GitHubRepositoryApiService {
    @GET(APIConstants.GET_REPOSITORIES)
    suspend fun getRepositories(): Response<List<GitHubRepositoryModelNetwork>>
}