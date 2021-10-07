package com.wtmcodex.androidsamplegithub.mapper

import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.core.data.model.backend.GitHubRepositoryModelNetwork

class GitHubMapperRemote :
    BaseMapper<List<GitHubRepositoryModelNetwork>, List<GitHubRepositoryModel>> {
    override fun transformToDomain(type: List<GitHubRepositoryModelNetwork>): List<GitHubRepositoryModel> {
        return type.map { gitHubRepositoryModelNetwork ->
            GitHubRepositoryModel(
                id = gitHubRepositoryModelNetwork.id,
                name = gitHubRepositoryModelNetwork.name,
                fullName = gitHubRepositoryModelNetwork.fullName,
                starsCount = gitHubRepositoryModelNetwork.starsCount,
                isBookMarked = false
            )
        }
    }

    override fun transformToDto(type: List<GitHubRepositoryModel>): List<GitHubRepositoryModelNetwork> {
        return arrayListOf()
    }
}