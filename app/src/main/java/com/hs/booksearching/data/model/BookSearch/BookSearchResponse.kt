package com.hs.booksearching.data.model.BookSearch

import com.google.gson.annotations.SerializedName
import com.hs.booksearching.domain.model.Book

data class BookSearchResponse(
    val display: Int,
    @SerializedName("items")
    val books: List<Book>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)