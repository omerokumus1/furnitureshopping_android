package com.omerokumus.furnitureshopping.feature.main.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseFragment
import com.omerokumus.furnitureshopping.base.recyclerview.BottomMarginItemDecoration
import com.omerokumus.furnitureshopping.data.BookmarkData
import com.omerokumus.furnitureshopping.databinding.FragmentBookmarksBinding

class BookmarksFragment : FurnitureBaseFragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var bookmarkAdapter: BookmarkItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBookmarksBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookmarksList.adapter =
            BookmarkItemAdapter(BookmarkData.bookmarkData).also { bookmarkAdapter = it }
        binding.bookmarksList.addItemDecoration(
            BottomMarginItemDecoration(
                resources.getDimension(R.dimen.bookmarks_item_margin).toInt()
            )
        )
    }
}