package com.omerokumus.furnitureshopping.feature.main.home.presentation.model

import com.omerokumus.furnitureshopping.feature.main.home.data.model.ProductResponse
import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse

data class ProductItem(
    val id: Int,
    val name: String,
    val price: Double,
    val priceUnit: String,
    val mainImage: String,
) {
    companion object {
        fun from(productResponse: ProductResponse?) = productResponse?.let {
            ProductItem(
                id = it.id ?: 0,
                name = it.name ?: "",
                price = it.price ?: 0.0,
                priceUnit = it.priceUnit ?: "",
                mainImage = it.mainImage ?: ""
            )
        }
        fun from(productDetailResponse: ProductDetailResponse?) = productDetailResponse?.let {
            ProductItem(
                id = it.id ?: 0,
                name = it.name ?: "",
                price = it.price ?: 0.0,
                priceUnit = it.priceUnit ?: "",
                mainImage = it.mainImage ?: ""
            )
        }
    }
}
