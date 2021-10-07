package com.wtmcodex.androidsamplegithub.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wtmcodex.androidsamplegithub.R
import com.wtmcodex.androidsamplegithub.databinding.GithubRepositoryCardViewItemBinding
import java.util.*

class GitHubRepositoryRecyclerViewAdapter internal constructor(private val onItemClickedListener: OnItemClickedListener?) :
    RecyclerView.Adapter<GitHubRepositoryRecyclerViewAdapter.RepositoryViewHolder>() {
    private var viewItems: List<GitHubRepositoryViewItem?>? = ArrayList()
    fun setViewItems(viewItems: List<GitHubRepositoryViewItem?>?) {
        this.viewItems = viewItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return viewItems!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            GithubRepositoryCardViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val viewItem = viewItems!![position]
        holder.bind(viewItem!!, position)
    }

    inner class RepositoryViewHolder internal constructor(private val itemBinding: GithubRepositoryCardViewItemBinding) :
        RecyclerView.ViewHolder(
            itemBinding.root
        ) {
        fun bind(viewItem: GitHubRepositoryViewItem, position: Int) {
            itemBinding.container.setOnClickListener {
                onItemClickedListener?.onItemClicked(
                    viewItem,
                    position
                )
            }
            itemBinding.tvTitle.text = viewItem.name
            itemBinding.tvStarsCount.text =
                "${itemBinding.container.context.getString(R.string.home_stars)} ${viewItem.starsCount}"
            val imageResource =
                if (viewItem.isBookMarked) R.drawable.star_filled else R.drawable.star_unfilled
            itemBinding.ivBookmark.setImageResource(imageResource)

        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(item: GitHubRepositoryViewItem, position: Int)
    }
}