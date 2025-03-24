package com.omerokumus.furnitureshopping.feature.main.bookmarks.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarkRepository@Inject constructor(private val remoteDataSource: BookmarkRemoteDataSource) {

    suspend fun getFavoriteProducts(userId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.getFavoriteProducts(userId)
    }

    suspend fun removeFavoriteProduct(userId: Int, productId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.removeFavoriteProduct(userId, productId)
    }
}