package com.wtmcodex.androidsamplegithub.core.data.source.remote

import com.wtmcodex.androidsamplegithub.core.data.model.backend.GitHubRepositoryModelNetwork
import com.wtmcodex.androidsamplegithub.core.data.source.remote.retrofit.GitHubRepositoryApiService
import com.wtmcodex.androidsamplegithub.core.di.scope.IoDispatcher
import com.wtmcodex.androidsamplegithub.helpers.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepositoryRemoteDataSourceImpl @Inject constructor(
    private val apiService: GitHubRepositoryApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    GitHubRepositoryRemoteDataSource {
    override suspend fun getRepositories(): Result<List<GitHubRepositoryModelNetwork>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.getRepositories()
                if (result.isSuccessful) {
                    val networkWeather = result.body()
                    Result.Success(networkWeather)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }