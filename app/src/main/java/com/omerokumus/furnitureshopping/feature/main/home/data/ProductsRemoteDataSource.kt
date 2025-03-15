package com.omerokumus.furnitureshopping.feature.main.home.data

import retrofit2.Retrofit
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    private val api = retrofit.create(ProductsApi::class.java)
    suspend fun getProducts() = api.getProducts()
}