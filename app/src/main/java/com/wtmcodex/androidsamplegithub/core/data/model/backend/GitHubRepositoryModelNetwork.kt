package com.wtmcodex.androidsamplegithub.core.data.model.backend

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryModelNetwork(
    val id: Long = 0,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("stargazers_count") val starsCount: Long
)