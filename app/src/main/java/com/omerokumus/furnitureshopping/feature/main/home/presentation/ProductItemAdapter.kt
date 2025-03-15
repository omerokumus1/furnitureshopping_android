package com.omerokumus.furnitureshopping.feature.main.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.databinding.ProductItemBinding
import java.util.Locale

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
            productPrice.text = root.context.getString(
                R.string.price_format,
                String.format(Locale.ENGLISH, "%.2f", item.price)
            )
            productImg.setImageResource(item.imageResource)
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}