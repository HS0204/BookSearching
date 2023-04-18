package com.hs.booksearching.data.di

import android.content.Context
import androidx.room.Room
import com.hs.booksearching.data.db.SearchWordDao
import com.hs.booksearching.data.db.SearchWordDatabase
import com.hs.booksearching.data.repositories.recentSearch.SearchWordDataSource
import com.hs.booksearching.data.repositories.recentSearch.SearchWordDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideSearchWordDataSource(searchDao: SearchWordDao): SearchWordDataSource {
        return SearchWordDataSourceImpl(searchDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SearchWordDatabase {
        return Room.databaseBuilder(
            context,
            SearchWordDatabase::class.java, "recentWord"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchWordDao(searchWordDatabase: SearchWordDatabase): SearchWordDao {
        return searchWordDatabase.searchWordDao()
    }
}