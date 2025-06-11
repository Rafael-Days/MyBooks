package com.example.mybooks.repository

import android.content.Context
import com.example.mybooks.entity.BookEntity

class BookRepository private constructor(context: Context) {

    private val database = BookDatabaseHelper(context)

    private val books = mutableListOf<BookEntity>()

    init {
        database.readableDatabase
    }

    companion object {
        private lateinit var instance: BookRepository

        fun getInstance(context: Context): BookRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }

    }



    fun getAllBooks(): List<BookEntity> {
        return books
    }

    fun getFavoriteBooks(): List<BookEntity> {
        return books.filter { it.favorite }
    }

    fun getBookByID(id: Int): BookEntity? {
        return books.find { it.id == id }
    }

    fun toggleFavoriteStatus(id: Int) {
        val book = books.find { it.id == id }
        if (book != null) {
            book.favorite = !book.favorite
        }
    }

    fun deleteBook(id: Int): Boolean {
        return books.removeIf { it.id == id }
    }

}