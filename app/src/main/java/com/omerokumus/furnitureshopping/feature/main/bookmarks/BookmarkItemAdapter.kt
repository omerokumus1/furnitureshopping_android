package com.omerokumus.furnitureshopping.feature.main.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.constants.Constants
import com.omerokumus.furnitureshopping.databinding.BookmarkItemBinding
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.ProductItem
import java.util.Locale

class BookmarkItemAdapter(
    private val onClick: (ProductItem) -> Unit,
    private val onRemove: (ProductItem) -> Unit
) :
    RecyclerView.Adapter<BookmarkItemAdapter.ViewHolder>() {

    var bookmarkList = mutableListOf<ProductItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookmarkList[position], onClick, onRemove)
    }

    override fun getItemCount() = bookmarkList.size

    inner class ViewHolder(private val binding: BookmarkItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            bookmarkItem: ProductItem,
            onClick: (ProductItem) -> Unit,
            onRemove: (ProductItem) -> Unit
        ) {
            binding.apply {
                bookmarkTitle.text = bookmarkItem.name
                bookmarkPrice.text = String.format(Locale.ENGLISH, "$%.2f", bookmarkItem.price)
                Glide.with(root)
                    .load(Constants.IMAGE_RESOURCES_BASE_URL + bookmarkItem.mainImage)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_available)
                    .into(binding.bookmarkImage)
                //if (bookmarkItem.isInCart) bookmarkShoppingBag.setImageResource(R.drawable.ic_shopping_bag_filled)
                //else bookmarkShoppingBag.setImageResource(R.drawable.ic_shopping_bag)
                root.setOnClickListener { onClick(bookmarkItem) }
                bookmarkRemoveBtn.setOnClickListener { onRemove(bookmarkItem) }
            }
        }
    }
}