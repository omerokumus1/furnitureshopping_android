package com.omerokumus.furnitureshopping.feature.productdetail.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val remoteDataSource: ProductDetailRemoteDataSource
) {

    suspend fun addFavoriteProduct(userId: Int, productId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.addFavoriteProduct(userId, productId)
    }

    suspend fun removeFavoriteProduct(userId: Int, productId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.removeFavoriteProduct(userId, productId)
    }

    suspend fun getFavoriteProducts(userId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.getFavoriteProducts(userId)
    }

    suspend fun getProductById(productId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.getProductById(productId)

    }


}