package com.wtmcodex.androidsamplegithub.features.home

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wtmcodex.androidsamplegithub.R
import com.wtmcodex.androidsamplegithub.core.data.model.AlertModel
import com.wtmcodex.androidsamplegithub.databinding.FragmentHomeBinding
import com.wtmcodex.androidsamplegithub.features.BaseFragment
import com.wtmcodex.androidsamplegithub.features.home.adapter.GitHubRepositoryRecyclerViewAdapter
import com.wtmcodex.androidsamplegithub.features.home.adapter.GitHubRepositoryViewItem
import com.wtmcodex.androidsamplegithub.helpers.Utils

class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    override fun getBindingView(inflater: LayoutInflater?): View {
        binding = FragmentHomeBinding.inflate(inflater!!)
        return binding!!.root
    }


    override fun getOrCreateViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactoryProvider).get(
            HomeViewModel::class.java
        )
    }

    override fun setupUI() {
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    override fun setupBindings() {

        with(viewModel) {
            isLoading.observe(viewLifecycleOwner, { isLoading: Boolean? ->
                if (isLoading!!) {
                    binding!!.pbLoading.visibility = View.VISIBLE
                } else {
                    binding!!.pbLoading.visibility = View.GONE
                }
            })
            showRefreshIndicator.observe(
                viewLifecycleOwner,
                { showRefreshIndicator: Boolean? ->
                    binding!!.srLayout.isRefreshing = showRefreshIndicator!!
                })
            isRecordFound.observe(viewLifecycleOwner, { isRecordFound: Boolean? ->
                if (isRecordFound!!) {
                    binding!!.tvNoResult.visibility = View.GONE
                    binding!!.rvRepository.visibility = View.VISIBLE
                } else {
                    binding!!.tvNoResult.visibility = View.VISIBLE
                    binding!!.rvRepository.visibility = View.GONE
                }
            })
            viewItems.observe(
                viewLifecycleOwner,
                { viewItems: List<GitHubRepositoryViewItem?>? ->
                    val adapter =
                        binding!!.rvRepository.adapter as GitHubRepositoryRecyclerViewAdapter?
                    adapter!!.setViewItems(viewItems)
                })

            showErrorAlertDialog.observe(viewLifecycleOwner, { shouldShowErrorAlert ->
                if (shouldShowErrorAlert) {
                    showAlert(
                        AlertModel(
                            title = requireActivity().getString(R.string.title_error_in_request),
                            message = requireActivity().getString(R.string.message_error_in_request),
                            positiveButtonTitle = requireActivity().getString(R.string.alert_ok_label)
                        )
                    )
                }

            })
        }

    }

    override fun setListeners() {
        binding!!.srLayout.setOnRefreshListener {
            binding!!.srLayout.isRefreshing = true
            doOnRefresh()
        }
    }

    override fun loadData() {
        doApiCall(true)
    }

    private fun setupRecyclerView() {
        val adapter =
            GitHubRepositoryRecyclerViewAdapter(object :
                GitHubRepositoryRecyclerViewAdapter.OnItemClickedListener {
                override fun onItemClicked(item: GitHubRepositoryViewItem, position: Int) {
                    val repository = viewModel.getRepositoryFromList(position)
                    Navigation.findNavController(binding!!.root).navigate(
                        HomeFragmentDirections.actionHomeFragmentToGitHubRepositoryDetailFragment(
                            repository
                        )
                    )

                }

            })
        val lManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding!!.rvRepository.layoutManager = lManager
        binding!!.rvRepository.adapter = adapter
    }

    private fun setupSwipeRefreshLayout() {
        binding!!.srLayout.setColorSchemeColors(
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_blue_bright),
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_green_light),
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_orange_light),
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_red_light)
        )
    }

    private fun doOnRefresh() {
        doApiCall(false)
    }

    private fun doApiCall(showLoading: Boolean) {
        if (!Utils.isNetworkAvailable(requireContext())) {
            showAlert(
                AlertModel(
                    title = requireActivity().getString(R.string.title_no_internet_connection),
                    message = requireActivity().getString(R.string.message_no_internet_connection),
                    positiveButtonTitle = requireActivity().getString(R.string.alert_ok_label)
                )
            )

            binding!!.pbLoading.visibility = View.GONE
            binding!!.tvNoResult.visibility = View.VISIBLE
            binding!!.rvRepository.visibility = View.GONE
            binding!!.srLayout.isRefreshing = false
            return
        }
        viewModel.loadData(showLoading)
    }
}