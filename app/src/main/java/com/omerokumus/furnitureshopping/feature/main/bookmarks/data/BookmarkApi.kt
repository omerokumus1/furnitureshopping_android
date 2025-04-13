package com.omerokumus.furnitureshopping.feature.main.bookmarks.data

import com.omerokumus.furnitureshopping.feature.main.bookmarks.data.model.BookmarkItemResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface BookmarkApi {

    @GET("/api/users/{userId}/favorite-products")
    suspend fun getFavoriteProducts(@Path("userId") userId: Int): Response<List<BookmarkItemResponse>>

    @DELETE("/api/users/{userId}/remove-favorite-product/{productId}")
    suspend fun removeFavoriteProduct(
        @Path("userId") userId: Int,
        @Path("productId") productId: Int
    )
}