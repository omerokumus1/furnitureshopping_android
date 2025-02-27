package com.omerokumus.furnitureshopping.feature.productdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerokumus.furnitureshopping.databinding.ProductImgLayoutBinding

class ProductImgPagerAdapter(var productImgList: List<Int>) :
    RecyclerView.Adapter<ProductImgPagerAdapter.ProductImgViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImgViewHolder {
        val binding =
            ProductImgLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductImgViewHolder, position: Int) {
        holder.binding.productImg.setImageResource(productImgList[position])
    }

    override fun getItemCount(): Int {
        return productImgList.size
    }

    inner class ProductImgViewHolder(val binding: ProductImgLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}