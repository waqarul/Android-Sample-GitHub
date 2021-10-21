package com.wtmcodex.androidsamplegithub.constants

import android.annotation.SuppressLint

interface APIConstants {
    @SuppressLint("StaticFieldLeak")
    companion object {
        const val GET_REPOSITORIES = "repos"

        const val READ_TIME_OUT_DELAY = 2L
        const val CONNECT_TIME_OUT_DELAY = 2L
    }
}