package com.wtmcodex.androidsamplegithub.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepositoryModel(
    val id: Long = 0,
    val name: String,
    val fullName: String,
    val starsCount: Long,
    @Transient var isBookMarked: Boolean = false
) : Parcelable