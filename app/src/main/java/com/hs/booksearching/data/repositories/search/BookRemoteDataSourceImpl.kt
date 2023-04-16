package com.hs.booksearching.data.repositories.search

import com.hs.booksearching.data.api.BookSearchInterface
import com.hs.booksearching.data.model.BookSearch.BookSearchResponse
import javax.inject.Inject

class BookRemoteDataSourceImpl @Inject constructor(private val apiService: BookSearchInterface):
    BookRemoteDataSource {
    override suspend fun getBookList(query: String, sort: String): BookSearchResponse {
        return apiService.getBookList(query = query, sort =  sort)
    }
}