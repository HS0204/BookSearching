package com.hs.booksearching.domain.usecase

import com.hs.booksearching.domain.model.Book
import com.hs.booksearching.domain.repositories.BookSearchRepository
import javax.inject.Inject

class GetBookListUseCase @Inject constructor(private val repository: BookSearchRepository) {
    suspend fun excute(query: String, sort: String): List<Book> = repository.getBookList(query, sort)
}