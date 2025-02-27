package com.omerokumus.furnitureshopping.feature.productdetail

import androidx.annotation.DrawableRes

data class ProductDetail(
    val id: Int,
    val name: String,
    val price: Double,
    @DrawableRes val images: List<Int>,
    val description: String,

    )
