package com.wtmcodex.androidsamplegithub.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wtmcodex.androidsamplegithub.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_REPOSITORY)
class DBGitHubRepository(
    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) val repoID: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "stars_count") val starsCount: Long,
    @ColumnInfo(name = "is_bookmarked") val bookmarked: Boolean
)