package com.wtmcodex.androidsamplegithub.features

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wtmcodex.androidsamplegithub.core.data.model.AlertModel

abstract class BaseViewModel : ViewModel() {
    var showAlertDialog = MutableLiveData<AlertModel>()
        protected set

    protected abstract fun loadData(params: Bundle?)
}