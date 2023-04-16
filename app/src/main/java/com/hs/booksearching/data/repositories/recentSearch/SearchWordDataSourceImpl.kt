package com.hs.booksearching.data.repositories.recentSearch

import com.hs.booksearching.data.db.SearchWordDao
import com.hs.booksearching.data.model.recentSearch.SearchWordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchWordDataSourceImpl @Inject constructor(private val searchWordDao: SearchWordDao) :
    SearchWordDataSource {
    override suspend fun insertWord(word: SearchWordEntity) = searchWordDao.insertWord(word)

    override fun getAllWord(): Flow<List<SearchWordEntity>> = searchWordDao.getAllWord()

    override suspend fun deleteWord(keyWord: String) = searchWordDao.deleteWord(keyWord)
}