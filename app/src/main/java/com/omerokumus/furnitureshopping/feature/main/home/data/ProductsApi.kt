package com.omerokumus.furnitureshopping.feature.main.home.data

import com.omerokumus.furnitureshopping.feature.main.home.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductResponse>>
}