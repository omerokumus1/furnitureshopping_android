package com.omerokumus.furnitureshopping.feature.main.bookmarks.presentation.model

import com.omerokumus.furnitureshopping.feature.main.bookmarks.data.model.BookmarkItemResponse
import com.omerokumus.furnitureshopping.feature.productdetail.presentation.model.ProductDetail


data class BookmarkItem(
    val id: Int,
    val name: String,
    val mainImage: String,
    val price: Double,
    val priceUnit: String,
    var isInCart: Boolean = false
) {
    companion object {
        fun from(bookmarkItemResponse: BookmarkItemResponse?) = bookmarkItemResponse?.let {
            BookmarkItem(
                id = bookmarkItemResponse.id ?: 0,
                name = bookmarkItemResponse.name ?: "",
                mainImage = bookmarkItemResponse.mainImage ?: "",
                price = bookmarkItemResponse.price ?: 0.0,
                priceUnit = bookmarkItemResponse.priceUnit ?: "",
                isInCart = bookmarkItemResponse.isInCart ?: false
            )
        }

        fun from(productDetail: ProductDetail?) = productDetail?.let {
            BookmarkItem(
                id = productDetail.id,
                name = productDetail.name,
                mainImage = productDetail.imageNames[0],
                price = productDetail.price,
                priceUnit = productDetail.priceUnit,
                isInCart = false
            )
        }
    }
}
