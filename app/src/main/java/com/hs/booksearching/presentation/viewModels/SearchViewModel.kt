package com.hs.booksearching.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private var _query: String = ""

    // 책 리스트
    private var _bookList = MutableLiveData<MutableList<Book>>()
    val bookList: LiveData<MutableList<Book>> = _bookList

    // 최근 검색어
    private var _recentWordList: Flow<List<SearchWordItem>> = getAllWordUseCase.excute()
    val recentWordList = _recentWordList
    val hasFocus = MutableLiveData<Boolean>()

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

    private fun requestBookList(query: String) {
        viewModelScope.launch {
            _apiStatus.value = ApiStatus.LOADING

            try {
                _query = query
                val bookListResponse = getBookListUseCase.excute(_query, "sim")
                if (bookListResponse.isNotEmpty()) {
                    _bookList.value = bookListResponse as ArrayList<Book>
                }

                _apiStatus.value = ApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
            }

        }
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