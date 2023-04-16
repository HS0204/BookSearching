package com.hs.booksearching.presentation.view.recentSearch

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.hs.booksearching.domain.model.SearchWordItem
import com.hs.booksearching.presentation.viewModels.SearchViewModel
import kotlinx.coroutines.flow.Flow

@BindingAdapter("setRecentWordList")
fun RecyclerView.setAdapterItems(items: Flow<List<SearchWordItem>>?) {
    items?.let {
        it.asLiveData().observe((context as LifecycleOwner)) { list ->
            (adapter as RecentSearchAdapter).submitList(list)
        }
    } ?: run {
        // items가 null일 경우 처리할 코드 작성
    }
}

@BindingAdapter("setRecentWord")
fun TextView.setRecentWord(word: String) {
    this.text = word
}

@BindingAdapter("onFocusChanged")
fun EditText.detectFocusChange(viewModel: SearchViewModel) {
    onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        viewModel.onFocusChanged(hasFocus)
    }
}

@BindingAdapter("isVisible")
fun RecyclerView.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}