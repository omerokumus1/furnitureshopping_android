package com.omerokumus.furnitureshopping.feature.productdetail.data

import retrofit2.Retrofit
import javax.inject.Inject

class ProductDetailRemoteDataSource@Inject constructor(private val retrofit: Retrofit) {

    private val api = retrofit.create(ProductDetailsApi::class.java)

    suspend fun addFavoriteProduct(userId: Int, productId: Int) = api.addFavoriteProduct(userId, productId)

    suspend fun removeFavoriteProduct(userId: Int, productId: Int) = api.removeFavoriteProduct(userId, productId)

    suspend fun getProductById(productId: Int, userId: Int) = api.getProductById(productId, userId)

}