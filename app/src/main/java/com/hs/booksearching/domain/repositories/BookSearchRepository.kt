package com.hs.booksearching.domain.repositories

import com.hs.booksearching.domain.model.Book

interface BookSearchRepository {
    suspend fun getBookList(query: String, sort: String) : List<Book>
}