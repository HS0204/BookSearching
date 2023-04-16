package com.hs.booksearching.data.repositories.search

import com.hs.booksearching.data.model.BookSearch.BookSearchResponse

interface BookRemoteDataSource {
    suspend fun getBookList(query: String, sort: String): BookSearchResponse
}