package com.example.mybooks.ui

import android.content.DialogInterface
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mybooks.R
import com.example.mybooks.databinding.FragmentDetailsBinding
import com.example.mybooks.helper.BookConstants
import com.example.mybooks.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private var bookId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setListeners()
        setObservers()

        bookId = arguments?.getInt(BookConstants.KEY.BOOK_ID) ?: 0
        viewModel.getBookById(bookId)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.imageviewArrowBackDetails.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.buttonRemoveBook.setOnClickListener { handleRemove() }

        binding.checkboxFavorite.setOnClickListener { handleFavorite() }
    }

    private fun handleFavorite(){
        viewModel.favorite(bookId)
    }

    private fun handleRemove() {
        var builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.dialog_message_delete_item))
            .setPositiveButton(R.string.dialog_positive_button_yes) { dialog, which ->
                viewModel.deleteBook(bookId)
            }
            .setNegativeButton(R.string.dialog_negative_button_no) { dialog, which ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) {
            binding.textviewTitleDetails.text = it.titlle
            binding.textviewAuthorValue.text = it.author
            binding.textviewGenreValue.text = it.genre
            binding.checkboxFavorite.isChecked = it.favorite

            setGenreBackground(it.genre)
        }

        viewModel.bookRemoval.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_removed_successfully),
                    Toast.LENGTH_SHORT
                ).show()

                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun setGenreBackground(genre: String) {
        when (genre) {
            "Fantasia" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }

            "Terror" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_red)
            }

            else -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }
    }

}