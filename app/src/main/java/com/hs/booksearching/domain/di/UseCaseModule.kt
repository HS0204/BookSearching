package com.hs.booksearching.domain.di

import com.hs.booksearching.domain.repositories.BookSearchRepository
import com.hs.booksearching.domain.usecase.GetBookListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetBookListUseCase(bookSearchRepository: BookSearchRepository): GetBookListUseCase {
        return GetBookListUseCase(bookSearchRepository)
    }
}