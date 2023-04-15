package com.hs.booksearching.presentation.view.search

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hs.booksearching.R
import com.hs.booksearching.databinding.ItemBookInfoBinding
import com.hs.booksearching.domain.model.Book

class BookSearchAdapter(private val onItemClicked: (Book) -> Unit) : ListAdapter<Book, BookSearchAdapter.ViewHolder>(DiffCallback),
    Adapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemBookInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_book_info,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener { view ->
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onItemClicked(getItem(position))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return itemCount
    }

    override fun getItem(position: Int): Book {
        return super.getItem(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun getViewTypeCount(): Int {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException("Not implemented")
    }

    class ViewHolder(private val binding: ItemBookInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book

        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

}