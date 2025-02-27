package com.omerokumus.furnitureshopping.feature.main

import androidx.annotation.DrawableRes

data class CategoryItem(
    val id: Int,
    @DrawableRes val categoryIcon: Int,
    @DrawableRes val categoryIconSelected: Int,
    val name: String,
    val isClicked: Boolean = false,
)
