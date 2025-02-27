package com.omerokumus.furnitureshopping.feature.main.home

import androidx.annotation.DrawableRes

data class ProductItem(
    val id: Int,
    val name: String,
    val price: Double,
    @DrawableRes val imageResource: Int,
)
