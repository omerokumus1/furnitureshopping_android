package com.omerokumus.furnitureshopping.feature.main.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.databinding.CategoryItemBinding
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.CategoryItem

class CategoryItemAdapter(
    var values: List<CategoryItem>,
    private val onClick: (CategoryItem) -> Unit = {}
) : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.binding.run {
            categoryIcon.setImageResource(item.categoryIcon)
            categoryName.text = item.name
            root.setOnClickListener { onClick(item) }
            handleClickState(item)
        }
    }

    private fun CategoryItemBinding.handleClickState(item: CategoryItem) {
        if (item.isClicked) {
            categoryIcon.setImageResource(item.categoryIconSelected)
            categoryName.setTextColor(root.context.getColor(R.color.black))
            categoryName.typeface =
                ResourcesCompat.getFont(root.context, R.font.nunito_sans_semibold)
        } else {
            categoryIcon.setImageResource(item.categoryIcon)
            categoryName.setTextColor(root.context.getColor(R.color.icon_gray))
            categoryName.typeface =
                ResourcesCompat.getFont(root.context, R.font.nunito_sans)
        }
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)
}