package com.wtmcodex.androidsamplegithub.constants

interface DatabaseConstants {
    companion object {
        const val DATABASE_NAME = "GitHubRepository.db"
        const val TABLE_REPOSITORY = "Repository"
        const val QUERY_SELECT_REPOSITORIES = "SELECT * FROM $TABLE_REPOSITORY"
        const val QUERY_SELECT_REPOSITORY_BY_ID = "SELECT * FROM $TABLE_REPOSITORY WHERE id=:id "
        const val QUERY_DELETE_REPOSITORY = "DELETE FROM $TABLE_REPOSITORY"
    }
}