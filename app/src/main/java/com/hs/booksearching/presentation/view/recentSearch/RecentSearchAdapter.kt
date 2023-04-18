package com.hs.booksearching.presentation.view.recentSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hs.booksearching.databinding.ItemRecentWordBinding
import com.hs.booksearching.domain.model.SearchWordItem

class RecentSearchAdapter(private val onSearchClicked: (SearchWordItem) -> Unit,
                          private val onDeleteClicked: (SearchWordItem) -> Unit) :
    ListAdapter<SearchWordItem, RecentSearchAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            ItemRecentWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItem(position: Int): SearchWordItem {
        return super.getItem(position)
    }

    inner class ViewHolder(private val binding: ItemRecentWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: SearchWordItem) {
            binding.recentWord = word

            binding.itemRecentWordTxt.setOnClickListener {
                onSearchClicked(word)
            }
            binding.itemRecentWordDelBtn.setOnClickListener {
                onDeleteClicked(word)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<SearchWordItem>() {
            override fun areItemsTheSame(oldItem: SearchWordItem, newItem: SearchWordItem): Boolean {
                return oldItem.searchKeyWord == newItem.searchKeyWord
            }

            override fun areContentsTheSame(oldItem: SearchWordItem, newItem: SearchWordItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}