package com.hs.booksearching.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hs.booksearching.data.model.recentSearch.SearchWordEntity

@Database(entities = [SearchWordEntity::class], version = 1, exportSchema = false)
abstract class SearchWordDatabase : RoomDatabase() {
    abstract fun searchWordDao(): SearchWordDao
}