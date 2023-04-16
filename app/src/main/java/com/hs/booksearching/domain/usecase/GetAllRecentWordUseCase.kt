package com.hs.booksearching.domain.usecase

import com.hs.booksearching.domain.repositories.SearchWordRepository
import javax.inject.Inject

class GetAllSearchWordUseCase @Inject constructor(private val repository: SearchWordRepository) {
    fun excute() = repository.getAllWord()
}
