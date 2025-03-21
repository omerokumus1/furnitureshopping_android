package com.omerokumus.furnitureshopping.feature.usermanager

import com.omerokumus.furnitureshopping.feature.usermanager.model.User
import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse
import com.omerokumus.furnitureshopping.feature.productdetail.presentation.model.ProductDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    private var user: User? = null
    private var userFavoriteProducts: MutableList<ProductDetail> = mutableListOf()

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    fun clearUser() {
        user = null
    }

    fun setUserFavoriteProducts(products: List<ProductDetail>) {
        userFavoriteProducts = products.toMutableList()
    }

    fun getUserFavoriteProducts(): List<ProductDetail> {
        return userFavoriteProducts
    }

    fun addFavoriteProduct(product: ProductDetail) {
        userFavoriteProducts.add(product)
    }

    fun removeFavoriteProduct(product: ProductDetail) {
        userFavoriteProducts.remove(product)
    }
}