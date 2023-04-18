package com.hs.booksearching.data.repositories

import com.hs.booksearching.data.mapper.map
import com.hs.booksearching.data.mapper.mapperToSearchWordItem
import com.hs.booksearching.data.repositories.recentSearch.SearchWordDataSource
import com.hs.booksearching.domain.model.SearchWordItem
import com.hs.booksearching.domain.repositories.SearchWordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchWordRepositoryImpl @Inject constructor(
    private val searchWordDataSource: SearchWordDataSource
): SearchWordRepository {
    override suspend fun insertWord(word: SearchWordItem) = searchWordDataSource.insertWord(word.map())

    override fun getAllWord(): Flow<List<SearchWordItem>> {
        return searchWordDataSource.getAllWord().map { words ->
            mapperToSearchWordItem(words)
        }
    }

    override suspend fun deleteWord(keyWord: String) = searchWordDataSource.deleteWord(keyWord)
}