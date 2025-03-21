package com.omerokumus.furnitureshopping.feature.productdetail.data

import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductDetailsApi {

    @POST("/api/users/{userId}/add-favorite-product/{productId}")
    suspend fun addFavoriteProduct(@Path("userId") userId: Int, @Path("productId") productId: Int)

    @DELETE("/api/users/{userId}/remove-favorite-product/{productId}")
    suspend fun removeFavoriteProduct(@Path("userId") userId: Int, @Path("productId") productId: Int)

    @GET("/api/users/{userId}/favorite-products")
    suspend fun getFavoriteProducts(@Path("userId") userId: Int): Response<List<ProductDetailResponse>>

    @GET("/api/products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): Response<ProductDetailResponse>
}