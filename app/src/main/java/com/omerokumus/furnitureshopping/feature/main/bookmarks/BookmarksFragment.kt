package com.omerokumus.furnitureshopping.feature.main.bookmarks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseFragment
import com.omerokumus.furnitureshopping.base.data.ToolbarLeftIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData
import com.omerokumus.furnitureshopping.base.recyclerview.BottomMarginItemDecoration
import com.omerokumus.furnitureshopping.data.BookmarkData
import com.omerokumus.furnitureshopping.databinding.FragmentBookmarksBinding
import com.omerokumus.furnitureshopping.feature.productdetail.ProductDetailActivity

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
        initToolbar()
        binding.bookmarksList.run {
            adapter =
                BookmarkItemAdapter(
                    BookmarkData.bookmarkData,
                    ::onClickBookmarkItem
                ).also { bookmarkAdapter = it }
            addItemDecoration(
                BottomMarginItemDecoration(
                    resources.getDimension(R.dimen.bookmarks_item_margin).toInt()
                )
            )
        }
    }

    private fun initToolbar() {
        furnitureBaseActivity.run {
            setToolbarTitleData(ToolbarTitleData(visibility = View.GONE))
            setToolbarSubTitleData(ToolbarSubTitleData("Favorites"))
            setToolbarLeftIconData(
                ToolbarLeftIconData(
                    visibility = View.GONE,
                    contentDescription = ""
                )
            )
            setToolbarRightIconData(
                ToolbarRightIconData(
                    visibility = View.GONE,
                    contentDescription = ""
                )
            )
        }
    }

    private fun onClickBookmarkItem() {
        Intent(requireContext(), ProductDetailActivity::class.java).also {
            startActivity(it)
        }
    }
}