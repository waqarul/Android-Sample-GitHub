package com.wtmcodex.androidsamplegithub

import com.wtmcodex.androidsamplegithub.core.data.model.backend.GitHubRepositoryModelNetwork

object TestUtils {
    val fakedListResult: List<GitHubRepositoryModelNetwork> = listOf(
        GitHubRepositoryModelNetwork(
            id = 0,
            name = "Test Name",
            fullName = "Test Full Name",
            starsCount = 10
        )
    )
}
