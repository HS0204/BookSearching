package com.hs.booksearching.data.di

import com.hs.booksearching.data.api.BookSearchInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookSearchApiModule {
    @Provides
    @Singleton
    fun provideBookSearchInterface(): BookSearchInterface {
        return BookSearchInterface.create()
    }
}