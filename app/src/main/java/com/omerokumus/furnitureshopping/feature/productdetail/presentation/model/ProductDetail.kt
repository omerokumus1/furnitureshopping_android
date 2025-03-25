package com.omerokumus.furnitureshopping.feature.productdetail.presentation.model

import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse

data class ProductDetail(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val priceUnit: String,
    val imageNames: List<String>,
    val colorCodes: List<String>,
    var isInFavoriteProducts: Boolean
    ){
    companion object{
        fun from(productDetailResponse: ProductDetailResponse?) = productDetailResponse?.let {
            ProductDetail(
                id = it.id ?: 0,
                name = it.name ?: "",
                description = it.description ?: "",
                price = it.price ?: 0.0,
                priceUnit = it.priceUnit ?: "",
                imageNames = it.imageNames ?: emptyList(),
                colorCodes = it.colorCodes ?: emptyList(),
                isInFavoriteProducts = it.isInFavoriteProducts ?: false
            )
        }
    }
}

