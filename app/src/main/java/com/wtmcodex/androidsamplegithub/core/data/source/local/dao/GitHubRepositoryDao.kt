package com.wtmcodex.androidsamplegithub.core.data.source.local.dao

import androidx.room.*
import com.wtmcodex.androidsamplegithub.constants.DatabaseConstants
import com.wtmcodex.androidsamplegithub.core.data.source.local.entity.DBGitHubRepository

@Dao
interface GitHubRepositoryDao : BaseDao<DBGitHubRepository> {

    @Insert
    suspend fun insertRepository(repository: DBGitHubRepository)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRepository(repository: DBGitHubRepository)

    @Query(DatabaseConstants.QUERY_SELECT_REPOSITORIES)
    suspend fun getAllRepositories(): List<DBGitHubRepository>?

    @Query(DatabaseConstants.QUERY_SELECT_REPOSITORY_BY_ID)
    suspend fun getRepositoryByID(id: Long): DBGitHubRepository?

    @Query(DatabaseConstants.QUERY_DELETE_REPOSITORY)
    suspend fun deleteAllRepositories()
}