package com.omerokumus.furnitureshopping.feature.productdetail.data

import com.omerokumus.furnitureshopping.data.ProductData
import retrofit2.Retrofit
import javax.inject.Inject

class ProductDetailRemoteDataSource@Inject constructor(private val retrofit: Retrofit) {

    private val api = retrofit.create(ProductDetailsApi::class.java)

    suspend fun addFavoriteProduct(userId: Int, productId: Int) = api.addFavoriteProduct(userId, productId)

    suspend fun removeFavoriteProduct(userId: Int, productId: Int) = api.removeFavoriteProduct(userId, productId)

    suspend fun getFavoriteProducts(userId: Int) = api.getFavoriteProducts(userId)

    suspend fun getProductById(productId: Int) = api.getProductById(productId)

//    val lampDetail = ProductData.productDetailData.find { it.id == 1 }
//
//    val drawerDetail = ProductData.productDetailData.find { it.id == 2 }
//
//    val chairDetail = ProductData.productDetailData.find { it.id == 3 }
//
//    val drawer2Detail = ProductData.productDetailData.find { it.id == 4 }
}