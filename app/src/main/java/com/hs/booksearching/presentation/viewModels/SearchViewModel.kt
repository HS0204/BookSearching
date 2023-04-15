package com.hs.booksearching.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hs.booksearching.domain.model.Book
import com.hs.booksearching.domain.usecase.GetBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase
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

    init {
        _apiStatus.value = ApiStatus.PREPARE
    }

    fun setInputQuery(query: String) {
        inputQuery.value = query
    }

    fun requestBookList() {
        viewModelScope.launch {
            _apiStatus.value = ApiStatus.LOADING
            val query = inputQuery.value

            try {
                if (query.isNullOrEmpty()) return@launch

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

}