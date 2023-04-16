package com.hs.booksearching.domain.repositories

import com.hs.booksearching.domain.model.SearchWordItem
import kotlinx.coroutines.flow.Flow

interface SearchWordRepository {
    suspend fun insertWord(word: SearchWordItem)
    fun getAllWord(): Flow<List<SearchWordItem>>
    suspend fun deleteWord(keyWord: String)
}