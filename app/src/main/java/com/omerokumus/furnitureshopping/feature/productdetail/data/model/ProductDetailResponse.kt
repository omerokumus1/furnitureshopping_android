package com.omerokumus.furnitureshopping.feature.productdetail.data.model

data class ProductDetailResponse (
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val priceUnit: String? = null,
    val mainImage: String? = null,
    val imageNames: List<String>? = null,
    val colorCodes: List<String>? = null
)