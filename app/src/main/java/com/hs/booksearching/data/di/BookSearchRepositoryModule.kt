package com.hs.booksearching.data.di

import com.hs.booksearching.data.repositories.BookSearchRepositoryImpl
import com.hs.booksearching.data.searching.BookRemoteDataSource
import com.hs.booksearching.domain.repositories.BookSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookSearchRepositoryModule {
    @Provides
    @Singleton
    fun provideBookSearchRepository(bookRemoteDataSource: BookRemoteDataSource): BookSearchRepository {
        return BookSearchRepositoryImpl(bookRemoteDataSource)
    }

}