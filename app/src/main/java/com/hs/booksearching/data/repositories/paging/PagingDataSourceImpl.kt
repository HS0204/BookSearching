package com.hs.booksearching.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hs.booksearching.domain.model.Book
import com.hs.booksearching.domain.usecase.GetBookListUseCase
import retrofit2.HttpException

class PagingDataSourceImpl(
    private val getBookListUseCase: GetBookListUseCase,
    private val query: String
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = getBookListUseCase.excute(query = query, sort = "sim", nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpException) {
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}