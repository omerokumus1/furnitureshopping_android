package com.omerokumus.furnitureshopping.feature.main.home.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ProductsRepository @Inject constructor(
    private val remoteDataSource: ProductsRemoteDataSource
) {

    // NOTE: withContext is soooo much important
    suspend fun getProducts() = withContext(Dispatchers.IO) {
        remoteDataSource.getProducts()
    }
}