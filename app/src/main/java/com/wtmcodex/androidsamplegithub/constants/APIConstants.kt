package com.wtmcodex.androidsamplegithub.constants

import android.annotation.SuppressLint
import com.wtmcodex.androidsamplegithub.R
import com.wtmcodex.androidsamplegithub.core.Domain

interface APIConstants {
    @SuppressLint("StaticFieldLeak")
    companion object {
        private val BASE_API_URL: String = Domain.instance.context.getString(R.string.base_url)
        private val PATH: String = Domain.instance.context.getString(R.string.path)
        val BASE_URL = String.format("%s/%s/", BASE_API_URL, PATH)
        const val GET_REPOSITORIES = "repos"

        const val READ_TIME_OUT_DELAY = 2L
        const val CONNECT_TIME_OUT_DELAY = 2L
    }
}