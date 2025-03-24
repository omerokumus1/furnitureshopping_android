package com.omerokumus.furnitureshopping.feature.auth.login.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.omerokumus.furnitureshopping.feature.auth.UserApi
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRemoteDataSource@Inject constructor(private val retrofit: Retrofit) {

    val api = retrofit.create(UserApi::class.java)

    private val auth: FirebaseAuth = Firebase.auth

    suspend fun getUserById(userId: Int) = api.getUserById(userId)

    suspend fun getUserByEmail(email: String) = api.getUserByEmail(email)

    suspend fun checkFirebaseAuthentication(email: String, password: String) = auth.signInWithEmailAndPassword(email, password)

    suspend fun checkLoggedInUser() = auth.currentUser != null

}