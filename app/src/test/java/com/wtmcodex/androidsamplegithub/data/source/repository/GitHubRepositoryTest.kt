package com.wtmcodex.androidsamplegithub.data.source.repository

import com.wtmcodex.androidsamplegithub.MainCoroutineRule
import com.wtmcodex.androidsamplegithub.TestUtils.fakedListResult
import com.wtmcodex.androidsamplegithub.core.data.model.GitHubRepositoryModel
import com.wtmcodex.androidsamplegithub.core.data.source.local.GitHubRepositoryLocalDataSource
import com.wtmcodex.androidsamplegithub.core.data.source.remote.GitHubRepositoryRemoteDataSource
import com.wtmcodex.androidsamplegithub.core.data.source.repository.GitHubRepositoryImpl
import com.wtmcodex.androidsamplegithub.helpers.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class GitHubRepositoryTest {
    @get:Rule
    var rule = MockitoJUnit.rule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: GitHubRepositoryRemoteDataSource

    @Mock
    private lateinit var localDataSource: GitHubRepositoryLocalDataSource

    private lateinit var systemUnderTest: GitHubRepositoryImpl

    @Before
    fun setUp() {
        systemUnderTest = GitHubRepositoryImpl(localDataSource, remoteDataSource, Dispatchers.Main)
    }

    @Test
    fun `assert that getRepositories fetches successfully from the remote source`() =
        mainCoroutineRule.runBlockingTest {
            `when`(remoteDataSource.getRepositories()).thenReturn(Result.Success(fakedListResult))

            val response = systemUnderTest.getRepositories()

            verify(remoteDataSource, times(1)).getRepositories()
            val fakeListItem = fakedListResult.get(0)

            when (response) {
                is Result.Success -> {
                    val repositoryList = response.data
                    MatcherAssert.assertThat(
                        repositoryList as List<GitHubRepositoryModel>,
                        `is`(CoreMatchers.notNullValue())
                    )
                    val repository = repositoryList.get(0)
                    MatcherAssert.assertThat(repository.id, `is`(fakeListItem.id))
                    MatcherAssert.assertThat(repository.name, `is`(fakeListItem.name))
                    MatcherAssert.assertThat(repository.fullName, `is`(fakeListItem.fullName))
                    MatcherAssert.assertThat(repository.starsCount, `is`(fakeListItem.starsCount))

                }
            }
        }
}