package com.hs.booksearching.presentation.view.search

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hs.booksearching.databinding.FragmentSearchBinding
import com.hs.booksearching.presentation.base.BaseFragment
import com.hs.booksearching.presentation.view.recentSearch.RecentSearchAdapter
import com.hs.booksearching.presentation.viewModels.SearchViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var bookAdapter: BookSearchAdapter
    private lateinit var recentAdapter: RecentSearchAdapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.bookViewModel = viewModel

        setAdapter()

        binding.root.isClickable = true
        binding.root.isFocusableInTouchMode = true

        viewModel.bookList.observe(viewLifecycleOwner) {
            bookAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setAdapter() {
        /**
         * book search
         */
        bookAdapter = BookSearchAdapter(
            onItemClicked = { book ->
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.link))
                try {
                    requireActivity().startActivity(webIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(requireContext(), "웹 사이트로 이동할 수 있는 앱이 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        binding.searchingListRv.adapter = bookAdapter as BookSearchAdapter

        val bookSearchLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchingListRv.layoutManager = bookSearchLayoutManager

        /**
         * recent word
         */
        recentAdapter = RecentSearchAdapter(
            onSearchClicked = { searchWordItem ->
                viewModel.setInputQuery(searchWordItem.searchKeyWord)
                viewModel.setOnClick()
            },
            onDeleteClicked = { searchWordItem ->
                viewModel.deleteWord(searchWordItem.searchKeyWord)
            }
        )

        binding.searchingRecentWordListRv.adapter = recentAdapter
        val recentWordLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchingRecentWordListRv.layoutManager = recentWordLayoutManager
    }

}