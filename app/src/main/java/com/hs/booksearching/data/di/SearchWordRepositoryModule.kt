package com.hs.booksearching.data.di

import com.hs.booksearching.data.repositories.SearchWordRepositoryImpl
import com.hs.booksearching.data.repositories.recentSearch.SearchWordDataSource
import com.hs.booksearching.domain.repositories.SearchWordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class SearchWordRepositoryModule {
    @Provides
    @Singleton
    fun provideBookSearchRepository(searchWordDataSource: SearchWordDataSource): SearchWordRepository {
        return SearchWordRepositoryImpl(searchWordDataSource)
    }

}