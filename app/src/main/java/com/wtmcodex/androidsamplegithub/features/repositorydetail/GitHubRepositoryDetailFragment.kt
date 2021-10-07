package com.wtmcodex.androidsamplegithub.features.repositorydetail

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.wtmcodex.androidsamplegithub.R
import com.wtmcodex.androidsamplegithub.databinding.FragmentRepositoryDetailBinding
import com.wtmcodex.androidsamplegithub.features.BaseFragment

class GitHubRepositoryDetailFragment : BaseFragment() {
    private var binding: FragmentRepositoryDetailBinding? = null
    private lateinit var viewModel: GitHubRepositoryDetailViewModel
    override fun getBindingView(inflater: LayoutInflater?): View {
        binding = FragmentRepositoryDetailBinding.inflate(inflater!!)
        return binding!!.root
    }


    override fun getOrCreateViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactoryProvider).get(
            GitHubRepositoryDetailViewModel::class.java
        )
    }

    override fun setupUI() {

    }

    override fun setupBindings() {
        with(viewModel) {
            gitHubRepository.observe(
                viewLifecycleOwner,
                { gitHubRepository ->
                    run {
                        binding!!.gitHubRepository = gitHubRepository
                        val imageResource =
                            if (gitHubRepository.isBookMarked) R.drawable.star_filled else R.drawable.star_unfilled
                        binding!!.ivBookmark.setImageResource(imageResource)
                    }

                })
        }

    }

    override fun setListeners() {
        binding!!.ivBookmark.setOnClickListener {
            viewModel.updateRepositoryBookmark()
        }
    }

    override fun loadData() {
        viewModel.loadData(arguments)

        val args = GitHubRepositoryDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.updateData(args.gitHubRepository)
    }

}