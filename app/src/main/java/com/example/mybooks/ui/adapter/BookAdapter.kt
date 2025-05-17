package com.example.mybooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mybooks.databinding.ItemBookBinding
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.ui.viewhollder.BookViewHollder

class BookAdapter : RecyclerView.Adapter<BookViewHollder>() {

    private var bookList:List<BookEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHollder {
        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BookViewHollder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHollder, position: Int) {
        holder.bind(bookList[position])
    }

    fun updateBooks(list: List<BookEntity>){
        bookList = list
    }

}