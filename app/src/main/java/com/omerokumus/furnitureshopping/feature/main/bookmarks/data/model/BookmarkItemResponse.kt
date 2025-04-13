package com.omerokumus.furnitureshopping.feature.main.bookmarks.data.model

data class BookmarkItemResponse(
    val id: Int? = null,
    val name: String? = null,
    val mainImage: String? = null,
    val price: Double? = null,
    val priceUnit: String? = null,
    var isInCart: Boolean? = null
)