package com.hs.booksearching.domain.model

data class Book(
    val author: String,
    val description: String,
    val discount: String,
    val image: String,
    val isbn: String,
    val link: String,
    val pubdate: String,
    val publisher: String,
    val title: String
)