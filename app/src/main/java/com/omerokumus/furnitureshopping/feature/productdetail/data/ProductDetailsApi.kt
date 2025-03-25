package com.omerokumus.furnitureshopping.feature.productdetail.data

import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductDetailsApi {

    @POST("/api/users/{userId}/add-favorite-product/{productId}")
    suspend fun addFavoriteProduct(@Path("userId") userId: Int, @Path("productId") productId: Int)

    @DELETE("/api/users/{userId}/remove-favorite-product/{productId}")
    suspend fun removeFavoriteProduct(@Path("userId") userId: Int, @Path("productId") productId: Int)

    @GET("/api/products/{id}")
    suspend fun getProductById(@Path("id") productId: Int, @Query("userId") userId: Int): Response<ProductDetailResponse>
}