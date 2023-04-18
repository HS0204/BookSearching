package com.hs.booksearching.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.hs.booksearching.data.model.recentSearch.SearchWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchWordDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertWord(word: SearchWordEntity)

    @Query("SELECT * FROM recentSearchWord ORDER BY date DESC LIMIT 10")
    fun getAllWord(): Flow<List<SearchWordEntity>>

    @Query("DELETE FROM recentSearchWord WHERE searchKeyWord = :keyWord")
    suspend fun deleteWord(keyWord: String)
}