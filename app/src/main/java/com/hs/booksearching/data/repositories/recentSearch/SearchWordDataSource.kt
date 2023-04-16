package com.hs.booksearching.data.repositories.recentSearch

import com.hs.booksearching.data.model.recentSearch.SearchWordEntity
import kotlinx.coroutines.flow.Flow

interface SearchWordDataSource {
    suspend fun insertWord(word: SearchWordEntity)
    fun getAllWord(): Flow<List<SearchWordEntity>>
    suspend fun deleteWord(keyWord: String)
}