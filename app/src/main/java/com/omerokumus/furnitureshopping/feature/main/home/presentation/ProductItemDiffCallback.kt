package com.omerokumus.furnitureshopping.feature.main.home.presentation

import androidx.recyclerview.widget.DiffUtil
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.ProductItem

class ProductItemDiffCallback(
    private val oldList: List<ProductItem>,
    private val newList: List<ProductItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Check if the items are the same (e.g., same ID)
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Check if the contents of the items are the same
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}