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

    enum class ApiStatus { PREPARE, LOADING, ERROR, DONE }

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    // 검색어
    val inputQuery = MutableLiveData<String>()

    // 최근 검색어
    private var _recentWordList: Flow<List<SearchWordItem>> = getAllWordUseCase.excute()
    val recentWordList = _recentWordList
    val hasFocus = MutableLiveData<Boolean>()

    // 책 리스트
    val bookList: LiveData<PagingData<Book>> = inputQuery.switchMap { query ->
        requestBookList(query).asLiveData()
    }

    init {
        _apiStatus.value = ApiStatus.PREPARE
    }

    fun onFocusChanged(hasFocus: Boolean) {
        this.hasFocus.value = hasFocus
    }

    fun setInputQuery(query: String) {
        inputQuery.value = query
    }

    fun setOnClick() {
        val query = inputQuery.value
        if (query.isNullOrEmpty()) return

        requestBookList(query)
        insertWord(query)
        onFocusChanged(false)
    }

    private fun requestBookList(query: String): Flow<PagingData<Book>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            PagingDataSourceImpl(getBookListUseCase, query)
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