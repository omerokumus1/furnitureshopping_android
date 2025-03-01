package com.omerokumus.furnitureshopping.feature.main.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.databinding.BookmarkItemBinding
import java.util.Locale

class BookmarkItemAdapter(
    private val bookmarkList: List<BookmarkItem>,
    private val onClick: () -> Unit
) :
    RecyclerView.Adapter<BookmarkItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookmarkList[position], onClick)
    }

    override fun getItemCount() = bookmarkList.size

    inner class ViewHolder(private val binding: BookmarkItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmarkItem: BookmarkItem, onClick: () -> Unit) {
            binding.apply {
                bookmarkTitle.text = bookmarkItem.name
                bookmarkPrice.text = String.format(Locale.ENGLISH, "$%.2f", bookmarkItem.price)
                bookmarkImage.setImageResource(bookmarkItem.imageResId)
                if (bookmarkItem.isInCart) bookmarkShoppingBag.setImageResource(R.drawable.ic_shopping_bag_filled)
                else bookmarkShoppingBag.setImageResource(R.drawable.ic_shopping_bag)
                root.setOnClickListener { onClick() }
            }
        }
    }
}