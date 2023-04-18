package com.hs.booksearching.domain.usecase

import com.hs.booksearching.domain.model.SearchWordItem
import com.hs.booksearching.domain.repositories.SearchWordRepository
import javax.inject.Inject

class InsertSearchWordUseCase @Inject constructor(private val repository: SearchWordRepository) {
    suspend fun excute(word: SearchWordItem) = repository.insertWord(word)
}