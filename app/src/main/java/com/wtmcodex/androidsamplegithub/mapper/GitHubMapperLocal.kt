package com.wtmcodex.androidsamplegithub.mapper

import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.core.data.source.local.entity.DBGitHubRepository

class GitHubMapperLocal :
    BaseMapper<DBGitHubRepository, GitHubRepositoryModel> {
    override fun transformToDomain(type: DBGitHubRepository): GitHubRepositoryModel =
        GitHubRepositoryModel(
            id = type.repoID,
            name = type.name,
            fullName = type.fullName,
            starsCount = type.starsCount,
            isBookMarked = type.bookmarked
        )


    override fun transformToDto(type: GitHubRepositoryModel): DBGitHubRepository {
        return DBGitHubRepository(
            repoID = type.id,
            name = type.name,
            fullName = type.fullName,
            starsCount = type.starsCount,
            bookmarked = type.isBookMarked
        )
    }
}