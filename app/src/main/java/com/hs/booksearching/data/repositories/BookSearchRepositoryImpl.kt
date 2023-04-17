package com.hs.booksearching.data.repositories

import com.hs.booksearching.domain.model.Book
import com.hs.booksearching.data.repositories.search.BookRemoteDataSource
import com.hs.booksearching.domain.repositories.BookSearchRepository
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
): BookSearchRepository {
    override suspend fun getBookList(query: String, sort: String, start: Int): List<Book> {
        return bookRemoteDataSource.getBookList(query = query, sort = sort, start = start).books
    }
}