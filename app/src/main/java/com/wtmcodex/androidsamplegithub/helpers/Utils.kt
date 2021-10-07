package com.wtmcodex.androidsamplegithub.helpers

import android.content.Context
import android.net.ConnectivityManager
import com.wtmcodex.androidsamplegithub.core.Domain

object Utils {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            Domain.instance.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }
}