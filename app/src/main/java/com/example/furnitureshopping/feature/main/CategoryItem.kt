package com.example.furnitureshopping.feature.main

import androidx.annotation.DrawableRes

data class CategoryItem(
    val id: Int,
    @DrawableRes val categoryIcon: Int,
    val name: String,
    val isClicked: Boolean = false,
    val onClick: () -> Unit = {}
)
