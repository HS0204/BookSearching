package com.hs.booksearching.data.model.recentSearch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentSearchWord")
data class SearchWordEntity(
    @PrimaryKey(autoGenerate = false)
    val searchKeyWord: String,
    val date: Long
)