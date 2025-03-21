package com.omerokumus.furnitureshopping.feature.productdetail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.constants.Constants
import com.omerokumus.furnitureshopping.databinding.ProductImgLayoutBinding

class ProductImgPagerAdapter(var productImgList: List<String>) :
    RecyclerView.Adapter<ProductImgPagerAdapter.ProductImgViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImgViewHolder {
        val binding =
            ProductImgLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductImgViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Constants.BASE_URL + "products/images/" + productImgList[position])
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_not_available)
            .into(holder.binding.productImg)

    }

    override fun getItemCount(): Int {
        return productImgList.size
    }

    inner class ProductImgViewHolder(val binding: ProductImgLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}