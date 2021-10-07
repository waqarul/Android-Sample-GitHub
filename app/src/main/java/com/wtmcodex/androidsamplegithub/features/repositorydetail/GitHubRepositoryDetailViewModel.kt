package com.wtmcodex.androidsamplegithub.features.repositorydetail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.core.data.source.repository.GitHubRepository
import com.wtmcodex.androidsamplegithub.features.BaseViewModel
import com.wtmcodex.androidsamplegithub.helpers.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitHubRepositoryDetailViewModel @Inject constructor(private val repository: GitHubRepository) :
    BaseViewModel() {
    private val _gitHubRepository = MutableLiveData<GitHubRepositoryModel>()
    val gitHubRepository = _gitHubRepository.asLiveData()

    lateinit var gitHubRepositoryDetail: GitHubRepositoryModel

    public override fun loadData(params: Bundle?) {
    }

    fun updateData(gitHubRepositoryModel: GitHubRepositoryModel) {
        viewModelScope.launch {
            gitHubRepositoryDetail = gitHubRepositoryModel
            gitHubRepositoryDetail.isBookMarked =
                repository.isRepositoryBookMarked(gitHubRepositoryModel.id)
            _gitHubRepository.value = gitHubRepositoryDetail
        }
    }

    fun updateRepositoryBookmark() {
        viewModelScope.launch {
            gitHubRepositoryDetail.isBookMarked = !gitHubRepositoryDetail.isBookMarked
            repository.shouldBookMarRepository(gitHubRepositoryDetail)
            _gitHubRepository.value = repository.getRepositoryByID(gitHubRepositoryDetail.id)
        }
    }
}