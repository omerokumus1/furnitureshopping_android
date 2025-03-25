package com.omerokumus.furnitureshopping.feature.usermanager

import com.omerokumus.furnitureshopping.feature.main.bookmarks.presentation.model.BookmarkItem
import com.omerokumus.furnitureshopping.feature.usermanager.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    private var user: User? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    fun clearUser() {
        user = null
    }
}