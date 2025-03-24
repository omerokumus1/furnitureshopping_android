package com.omerokumus.furnitureshopping.feature.main.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.constants.Constants
import com.omerokumus.furnitureshopping.databinding.ProductItemBinding
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.ProductItem
import java.util.Locale

class ProductItemAdapter(
    private var values: List<ProductItem>,
    private val onClick: (ProductItem) -> Unit = {}
) : RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

    fun submitList(newList: List<ProductItem>) {
        val diffResult = DiffUtil.calculateDiff(ProductItemDiffCallback(values, newList))
        values = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.binding.run {
            productName.text = item.name
            productPrice.text = String.format(Locale.ENGLISH, "${item.priceUnit}%.2f", item.price)
            Glide.with(root)
                .load(Constants.IMAGE_RESOURCES_BASE_URL + item.mainImage)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_available)
                .into(productImg)
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}