package com.wtmcodex.androidsamplegithub.core.data.source.repository

import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.core.data.source.local.GitHubRepositoryLocalDataSource
import com.wtmcodex.androidsamplegithub.core.data.source.remote.GitHubRepositoryRemoteDataSource
import com.wtmcodex.androidsamplegithub.core.di.scope.IoDispatcher
import com.wtmcodex.androidsamplegithub.helpers.Result
import com.wtmcodex.androidsamplegithub.mapper.GitHubMapperLocal
import com.wtmcodex.androidsamplegithub.mapper.GitHubMapperRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val localDataSource: GitHubRepositoryLocalDataSource,
    private val remoteDataSource: GitHubRepositoryRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GitHubRepository {

    override suspend fun getRepositories(): Result<List<GitHubRepositoryModel>> =
        withContext(ioDispatcher) {

            when (val response = remoteDataSource.getRepositories()) {
                is Result.Success -> {
                    if (response.data != null) {
                        val mapper = GitHubMapperRemote()
                        val repositoryList = mapper.transformToDomain(response.data)
                        val localRepositoryList = localDataSource.getAllRepositories()
                        if (localRepositoryList.isNullOrEmpty()) {
                            Result.Success(repositoryList)
                        } else {
                            repositoryList.forEach { remoteRepository ->
                                localRepositoryList.filter { remoteRepository.id == it.repoID }
                                    .forEach { remoteRepository.isBookMarked = it.bookmarked }
                            }
                            Result.Success(repositoryList)
                        }

                    } else {
                        Result.Success(null)
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }

                else -> Result.Loading
            }

        }

    override suspend fun getRepositoryByID(id: Long): GitHubRepositoryModel? =
        withContext(ioDispatcher) {
            val dbRepository = localDataSource.getRepositoryByID(id) ?: return@withContext null
            val mapper = GitHubMapperLocal()
            return@withContext mapper.transformToDomain(dbRepository)
        }

    override suspend fun isRepositoryBookMarked(id: Long): Boolean = withContext(ioDispatcher) {
        val repository = localDataSource.getRepositoryByID(id)
        return@withContext repository?.bookmarked == true
    }

    override suspend fun shouldBookMarRepository(gitHubRepositoryModel: GitHubRepositoryModel) =
        withContext(ioDispatcher) {
            val mapper = GitHubMapperLocal()
            localDataSource.insertOrUpdate(mapper.transformToDto(gitHubRepositoryModel))
        }

    override suspend fun deleteAllRepositories() = withContext(ioDispatcher) {
        localDataSource.deleteAllRepositories()
    }
}