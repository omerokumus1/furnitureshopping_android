package com.omerokumus.furnitureshopping.feature.auth.login.data

import retrofit2.Retrofit
import javax.inject.Inject

class LoginRemoteDataSource@Inject constructor(private val retrofit: Retrofit) {

    val api = retrofit.create(UserApi::class.java)



    suspend fun getUserById(userId: Int) = api.getUserById(userId)

}