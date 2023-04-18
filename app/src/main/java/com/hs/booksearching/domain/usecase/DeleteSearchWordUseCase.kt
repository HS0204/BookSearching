package com.hs.booksearching.domain.usecase

import com.hs.booksearching.domain.repositories.SearchWordRepository
import javax.inject.Inject

class DeleteSearchWordUseCase @Inject constructor(private val repository: SearchWordRepository) {
    suspend fun excute(keyWord: String) = repository.deleteWord(keyWord)
}