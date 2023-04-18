package com.hs.booksearching.data.mapper

import com.hs.booksearching.data.model.recentSearch.SearchWordEntity
import com.hs.booksearching.domain.model.SearchWordItem

fun mapperToSearchWordItem(words: List<SearchWordEntity>): List<SearchWordItem> {
    return words.toList().map {
        SearchWordItem(
            it.searchKeyWord,
            it.date
        )
    }
}

fun SearchWordEntity.map() = SearchWordItem(
    searchKeyWord,
    date
)

fun mapperToSearchWordEntity(words: List<SearchWordItem>): List<SearchWordEntity> {
    return words.toList().map {
        SearchWordEntity(
            it.searchKeyWord,
            it.date
        )
    }
}

fun SearchWordItem.map() = SearchWordEntity(
    searchKeyWord,
    date
)