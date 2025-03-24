package com.omerokumus.furnitureshopping.feature.auth.forgetpassword.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.omerokumus.furnitureshopping.feature.auth.UserApi
import retrofit2.Retrofit
import javax.inject.Inject

class ForgetPasswordRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    val api = retrofit.create(UserApi::class.java)

    val auth: FirebaseAuth = Firebase.auth

    suspend fun getUserByEmail(email: String) = api.getUserByEmail(email)

    suspend fun resetPassword(email: String) = auth.sendPasswordResetEmail(email)

}