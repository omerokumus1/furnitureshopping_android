package com.omerokumus.furnitureshopping.feature.productdetail.presentation.model

import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse

data class ProductDetail(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val priceUnit: String,
    val mainImage: String,
    val imageNames: List<String>,
    val colorCodes: List<String>
    ){
    companion object{
        fun from(productDetailResponse: ProductDetailResponse?) = productDetailResponse?.let {
            ProductDetail(
                id = it.id ?: 0,
                name = it.name ?: "",
                description = it.description ?: "",
                price = it.price ?: 0.0,
                priceUnit = it.priceUnit ?: "",
                mainImage = it.mainImage ?: "",
                imageNames = it.imageNames ?: emptyList(),
                colorCodes = it.colorCodes ?: emptyList()
            )
        }
    }
}

