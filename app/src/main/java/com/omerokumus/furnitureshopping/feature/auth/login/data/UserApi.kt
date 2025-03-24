package com.omerokumus.furnitureshopping.feature.auth.login.data

import com.omerokumus.furnitureshopping.feature.main.bookmarks.data.model.BookmarkItemResponse
import com.omerokumus.furnitureshopping.feature.productdetail.data.model.ProductDetailResponse
import com.omerokumus.furnitureshopping.feature.usermanager.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("/api/users/add-user")
    suspend fun addUser(@Path("fullName") fullName: String, @Path("email") email: String)

    @GET("/api/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): Response<User>

    @GET("/api/users/user-by-email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<User>

}