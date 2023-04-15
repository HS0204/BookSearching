package com.hs.booksearching.data.di

import com.hs.booksearching.data.api.BookSearchInterface
import com.hs.booksearching.data.searching.BookRemoteDataSource
import com.hs.booksearching.data.searching.BookRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookRemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideBookRemoteDataSource(bookSearchInterface: BookSearchInterface): BookRemoteDataSource {
        return BookRemoteDataSourceImpl(bookSearchInterface)
    }
}