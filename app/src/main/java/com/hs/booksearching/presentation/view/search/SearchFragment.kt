package com.hs.booksearching.presentation.view.search

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hs.booksearching.databinding.FragmentSearchBinding
import com.hs.booksearching.presentation.base.BaseFragment
import com.hs.booksearching.presentation.viewModels.SearchViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var bookAdapter: Adapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setAdapter()
    }

    private fun setAdapter() {
        bookAdapter = BookSearchAdapter {book ->
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.link))
            try {
                requireActivity().startActivity(webIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "웹 사이트로 이동할 수 있는 앱이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchingListRv.adapter = bookAdapter as BookSearchAdapter

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchingListRv.layoutManager = layoutManager
    }

}