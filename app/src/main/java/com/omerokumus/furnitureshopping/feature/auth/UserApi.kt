package com.omerokumus.furnitureshopping.feature.auth

import com.omerokumus.furnitureshopping.feature.auth.signup.data.model.UserRequest
import com.omerokumus.furnitureshopping.feature.usermanager.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("/api/users/add-user")
    suspend fun addUser(@Body userRequest: UserRequest)

    @GET("/api/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): Response<User>

    @GET("/api/users/user-by-email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<User>

}