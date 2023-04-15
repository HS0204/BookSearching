package com.hs.booksearching.presentation.view.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hs.booksearching.R
import com.hs.booksearching.domain.model.Book
import com.hs.booksearching.presentation.viewModels.SearchViewModel
import java.text.NumberFormat
import java.util.*

@BindingAdapter("urlImage")
fun ImageView.setUrlImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}

@BindingAdapter("bookTitle")
fun TextView.setTitle(title: String) {
    val formattedTitle = "제목: $title"
    this.text = formattedTitle
}

@BindingAdapter("bookWriter")
fun TextView.setWriter(writer: String) {
    val formattedWriter = "저자: ${if (writer == "") "-" else writer}"
    this.text = formattedWriter
}

@BindingAdapter("bookPublisher")
fun TextView.setPublisher(publisher: String) {
    val formattedPublisher = "출판사: ${if (publisher == "") "-" else publisher}"
    this.text = formattedPublisher
}

@BindingAdapter("bookPrice")
fun TextView.setPrice(price: String) {
    val formatter = NumberFormat.getInstance(Locale.KOREA)
    val formattedPrice = "가격: ${formatter.format(price.toDoubleOrNull() ?: 0.0)}원"
    this.text = formattedPrice
}

@BindingAdapter("setBookList")
fun RecyclerView.setAdapterItems(items: MutableList<Book>?) {
    items?.let {
        (adapter as BookSearchAdapter).submitList(it.toList())
    }
}

@BindingAdapter("loadingAnimVisible")
fun ViewFlipper.setVisibility(apiStatus: SearchViewModel.ApiStatus) {
    when (apiStatus) {
        SearchViewModel.ApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.startFlipping()
            flipInterval = 250
        }
        else -> {
            this.visibility = View.GONE
            this.stopFlipping()
        }
    }
}