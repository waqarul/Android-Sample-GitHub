package com.wtmcodex.androidsamplegithub.features.home

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.core.data.source.repository.GitHubRepository
import com.wtmcodex.androidsamplegithub.features.BaseViewModel
import com.wtmcodex.androidsamplegithub.features.home.adapter.GitHubRepositoryViewItem
import com.wtmcodex.androidsamplegithub.helpers.AlertMessagesHelper
import com.wtmcodex.androidsamplegithub.helpers.Result
import com.wtmcodex.androidsamplegithub.helpers.Utils
import com.wtmcodex.androidsamplegithub.helpers.asLiveData
import kotlinx.coroutines.launch
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repository: GitHubRepository) :
    BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _isRecordFound = MutableLiveData<Boolean>()
    val isRecordFound = _isRecordFound.asLiveData()

    private val _showRefreshIndicator = MutableLiveData<Boolean>()
    val showRefreshIndicator = _showRefreshIndicator.asLiveData()

    private val _viewItems = MutableLiveData<List<GitHubRepositoryViewItem>>()
    val viewItems = _viewItems.asLiveData()

    private val gitHubRepositoryModelList = ArrayList<GitHubRepositoryModel>()

    public override fun loadData(params: Bundle?) {
        makeRequestToFetchRepositories(true)
    }

    fun makeRequestToFetchRepositories(showLoading: Boolean) {
        makeRequest(showLoading)
    }

    private fun makeRequest(showLoading: Boolean) {
        if (!Utils.isNetworkAvailable()) {
            showAlertDialog.postValue(
                AlertMessagesHelper.getNoInternetConnectionAlertModel(null)
            )
            resetView()
            return
        }
        if (showLoading) {
            _isLoading.postValue(true)
        }

        viewModelScope.launch {
            when (val result = repository.getRepositories()) {
                is Result.Success -> {
                    if (!result.data.isNullOrEmpty()) {
                        val gitHubRepositories = result.data
                        _viewItems.value = getGitHubRepositoryViewItems(gitHubRepositories)
                        _isRecordFound.value = true
                    } else {
                        _isRecordFound.value = false
                    }

                    _isLoading.value = false
                    _showRefreshIndicator.value = false
                }
                is Result.Error -> {
                    resetView()
                    showAlertDialog.postValue(
                        AlertMessagesHelper.getAlertMessageFromError(null)
                    )
                }

                is Result.Loading -> _isLoading.value = true
            }
        }
    }

    private fun resetView() {
        _isLoading.postValue(false)
        _isRecordFound.postValue(false)
        _showRefreshIndicator.postValue(false)
    }

    private fun getGitHubRepositoryViewItems(list: List<GitHubRepositoryModel>): List<GitHubRepositoryViewItem> {
        gitHubRepositoryModelList.clear()
        gitHubRepositoryModelList.addAll(list)
        return gitHubRepositoryModelList.stream().map { item: GitHubRepositoryModel ->
            GitHubRepositoryViewItem(
                item.name,
                item.starsCount,
                item.isBookMarked
            )
        }.collect(Collectors.toList())
    }

    fun getRepositoryFromList(index: Int): GitHubRepositoryModel {
        return gitHubRepositoryModelList[index]
    }
}