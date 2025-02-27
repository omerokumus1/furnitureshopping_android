package com.omerokumus.furnitureshopping.feature.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerokumus.furnitureshopping.databinding.ProductItemBinding

class ProductItemAdapter(
    private val values: List<ProductItem>,
    private val onClick: (ProductItem) -> Unit = {}
) : RecyclerView.Adapter<ProductItemAdapter.ViewHolder>() {

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
            productPrice.text = "$${item.price}"
            productImg.setImageResource(item.imageResource)
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}