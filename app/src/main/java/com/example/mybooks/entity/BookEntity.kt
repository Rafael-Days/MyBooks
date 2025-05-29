package com.example.mybooks.entity

data class BookEntity (
    val id: Int,
    val tittle: String,
    val author: String,
    var favorite: Boolean,
    val genre: String,
)