package com.hs.booksearching.presentation.viewModels

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hs.booksearching.data.repositories.paging.PagingDataSourceImpl
import com.hs.booksearching.domain.model.Book
import com.hs.booksearching.domain.model.SearchWordItem
import com.hs.booksearching.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase,
    private val insertWordUseCase: InsertSearchWordUseCase,
    private val deleteWordUseCase: DeleteSearchWordUseCase,
    private val getAllWordUseCase: GetAllSearchWordUseCase,
) : ViewModel() {

    // 검색어
    val inputQuery = MutableLiveData<String>()

    // 최근 검색어
    private var _recentWordList: Flow<List<SearchWordItem>> = getAllWordUseCase.excute()
    val recentWordList = _recentWordList
    val hasFocus = MutableLiveData<Boolean>()

    fun onFocusChanged(hasFocus: Boolean) {
        this.hasFocus.value = hasFocus
    }

    fun setInputQuery(query: String) {
        inputQuery.value = query
    }

    fun setOnClick() {
        val query = inputQuery.value
        if (query.isNullOrEmpty()) return

        insertWord(query)
        onFocusChanged(false)
    }

    fun requestBookList(): Flow<PagingData<Book>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            PagingDataSourceImpl(getBookListUseCase, inputQuery.value!!)
        }.flow.cachedIn(viewModelScope)
    }

    private fun insertWord(word: String) {
        viewModelScope.launch {
            if (word.isEmpty()) return@launch

            val currentTimeMillis = System.currentTimeMillis()
            val insertWord = SearchWordItem(searchKeyWord = word, date = currentTimeMillis)
            insertWordUseCase.excute(insertWord)
        }
    }

    fun deleteWord(word: String) {
        viewModelScope.launch {
            if (word.isEmpty()) return@launch

            deleteWordUseCase.excute(word)
        }
    }

}