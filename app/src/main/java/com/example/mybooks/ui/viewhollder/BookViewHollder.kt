package com.example.mybooks.ui.viewhollder

import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.databinding.ItemBookBinding
import com.example.mybooks.entity.BookEntity

class BookViewHollder(private val item:ItemBookBinding):RecyclerView.ViewHolder(item.root) {

    fun bind(book: BookEntity){
        item.textviewTitle.text = book.titlle
        item.textviewAuthor.text = book.author
        item.textviewGenre.text = book.genre

        updateFavoriteIcon(book.favorite)
        setGenreBackground(book.genre)
    }

    private fun updateFavoriteIcon(favorite: Boolean){
        if (favorite){
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite)
        } else
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty)
    }

    private fun setGenreBackground(genre: String){
        when(genre) {
            "Fantasia" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }
            "Terror" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_red)
            }
            else -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }
    }
}