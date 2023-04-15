package com.hs.booksearching.data.searching

import com.hs.booksearching.data.model.BookSearch.BookSearchResponse

interface BookRemoteDataSource {
    suspend fun getBookList(query: String, sort: String): BookSearchResponse
}