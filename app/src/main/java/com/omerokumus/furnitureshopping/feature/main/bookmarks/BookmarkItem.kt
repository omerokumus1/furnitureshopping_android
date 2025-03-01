package com.omerokumus.furnitureshopping.feature.main.bookmarks

import androidx.annotation.DrawableRes

data class BookmarkItem(
    val id: Int,
    val productId: Int,
    val name: String,
    @DrawableRes val imageResId: Int,
    val price: Double,
    var isInCart: Boolean = false
)
