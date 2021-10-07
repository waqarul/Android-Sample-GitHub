package com.wtmcodex.androidsamplegithub.core.data.source.local

import com.wtmcodex.androidsamplegithub.core.data.source.local.dao.GitHubRepositoryDao
import com.wtmcodex.androidsamplegithub.core.data.source.local.entity.DBGitHubRepository
import com.wtmcodex.androidsamplegithub.core.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepositoryLocalDataSourceImpl @Inject constructor(
    private val repositoryDao: GitHubRepositoryDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    GitHubRepositoryLocalDataSource {
    override suspend fun insertAll(items: List<DBGitHubRepository>) = withContext(ioDispatcher) {
        return@withContext repositoryDao.insertAll(items)
    }

    override suspend fun insertOrUpdate(repository: DBGitHubRepository) =
        withContext(ioDispatcher) {
            val item = repositoryDao.getRepositoryByID(repository.repoID)
            if (item == null) {
                repositoryDao.insertRepository(repository)
            } else {
                repositoryDao.updateRepository(repository)
            }
        }

    override suspend fun getAllRepositories(): List<DBGitHubRepository>? =
        withContext(ioDispatcher) {
            return@withContext repositoryDao.getAllRepositories()
        }

    override suspend fun getRepositoryByID(id: Long): DBGitHubRepository? =
        withContext(ioDispatcher) {
            return@withContext repositoryDao.getRepositoryByID(id)
        }

    override suspend fun deleteAllRepositories() = withContext(ioDispatcher) {
        return@withContext repositoryDao.deleteAllRepositories()
    }
}