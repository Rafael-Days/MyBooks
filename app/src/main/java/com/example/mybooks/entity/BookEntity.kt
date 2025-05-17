package com.example.mybooks.entity

data class BookEntity (
    val id: Int,
    val titlle: String,
    val author: String,
    var favorite: Boolean,
    val genre: String,
)