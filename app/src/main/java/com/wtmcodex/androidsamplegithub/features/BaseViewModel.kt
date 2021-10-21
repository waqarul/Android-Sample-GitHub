package com.wtmcodex.androidsamplegithub.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wtmcodex.androidsamplegithub.helpers.asLiveData

abstract class BaseViewModel : ViewModel() {
    protected val _showErrorAlertDialog = MutableLiveData<Boolean>()
    val showErrorAlertDialog = _showErrorAlertDialog.asLiveData()
}