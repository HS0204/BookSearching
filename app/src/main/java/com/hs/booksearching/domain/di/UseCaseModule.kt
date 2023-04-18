package com.hs.booksearching.domain.di

import com.hs.booksearching.domain.repositories.BookSearchRepository
import com.hs.booksearching.domain.repositories.SearchWordRepository
import com.hs.booksearching.domain.usecase.*
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

    @Provides
    @Singleton
    fun provideInsertSearchWordUseCase(searchWordRepository: SearchWordRepository): InsertSearchWordUseCase {
        return InsertSearchWordUseCase(searchWordRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllSearchWordUseCase(searchWordRepository: SearchWordRepository): GetAllSearchWordUseCase {
        return GetAllSearchWordUseCase(searchWordRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSearchWordUseCase(searchWordRepository: SearchWordRepository): DeleteSearchWordUseCase {
        return DeleteSearchWordUseCase(searchWordRepository)
    }
}