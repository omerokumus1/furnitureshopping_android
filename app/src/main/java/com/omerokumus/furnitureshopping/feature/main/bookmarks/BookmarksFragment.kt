package com.omerokumus.furnitureshopping.feature.main.bookmarks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseFragment
import com.omerokumus.furnitureshopping.base.data.ToolbarLeftIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData
import com.omerokumus.furnitureshopping.base.recyclerview.MarginItemDecoration
import com.omerokumus.furnitureshopping.databinding.FragmentBookmarksBinding
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.ProductItem
import com.omerokumus.furnitureshopping.feature.productdetail.presentation.ProductDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : FurnitureBaseFragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var bookmarkAdapter: BookmarkItemAdapter
    private val viewModel: BookmarksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBookmarksBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initToolbar()
        viewModel.getBookmarks(1)
        binding.bookmarksList.run {
            adapter =
                BookmarkItemAdapter(
                    ::onClickBookmarkItem,
                    ::onRemoveBookmarkItem
                ).also { bookmarkAdapter = it }
            addItemDecoration(
                MarginItemDecoration(
                    bottom = resources.getDimension(R.dimen.bookmarks_item_margin).toInt()
                )
            )
        }
    }

    private fun initToolbar() {
        furnitureBaseActivity.run {
            setToolbarTitleData(ToolbarTitleData(visibility = View.GONE))
            setToolbarSubTitleData(ToolbarSubTitleData(requireContext().getString(R.string.favorites)))
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

    private fun onClickBookmarkItem(bookmarkItem: ProductItem) {
        Intent(requireContext(), ProductDetailActivity::class.java).also {
            it.putExtra("id", bookmarkItem.id)
            startActivity(it)
        }
    }

    private fun onRemoveBookmarkItem(bookmarkItem: ProductItem) {
        viewModel.removeFromBookmarks(1, bookmarkItem.id)
        bookmarkAdapter.bookmarkList.remove(bookmarkItem)
        bookmarkAdapter.notifyDataSetChanged()
    }

    private fun observeViewModel(){
        viewModel.bookmarksLiveData.observe(this){
            bookmarkAdapter.bookmarkList = it.toMutableList()
        }
    }
}