package com.omerokumus.furnitureshopping.feature.usermanager

import com.omerokumus.furnitureshopping.feature.main.bookmarks.presentation.model.BookmarkItem
import com.omerokumus.furnitureshopping.feature.usermanager.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    private var user: User? = null
    private var userFavoriteProducts: MutableList<BookmarkItem> = mutableListOf()

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    fun clearUser() {
        user = null
    }

    fun setUserFavoriteProducts(products: List<BookmarkItem>) {
        userFavoriteProducts = products.toMutableList()
    }

    fun getUserFavoriteProducts(): List<BookmarkItem> {
        return userFavoriteProducts
    }

    fun addFavoriteProduct(product: BookmarkItem?) {
        if (product != null) {
            userFavoriteProducts.add(product)
        }
    }

    fun removeFavoriteProduct(product: BookmarkItem?) {
        userFavoriteProducts.remove(product)
    }

    fun removeFavoriteProductById(productId: Int) {
        userFavoriteProducts.removeIf { it.id == productId }
    }
}