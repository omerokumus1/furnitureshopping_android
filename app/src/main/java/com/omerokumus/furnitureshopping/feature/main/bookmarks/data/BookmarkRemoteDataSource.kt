package com.omerokumus.furnitureshopping.feature.main.bookmarks.data

import retrofit2.Retrofit
import javax.inject.Inject

class BookmarkRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    private val api = retrofit.create(BookmarkApi::class.java)

    suspend fun getFavoriteProducts(userId: Int) = api.getFavoriteProducts(userId)

    suspend fun removeFavoriteProduct(userId: Int, productId: Int) =
        api.removeFavoriteProduct(userId, productId)

}