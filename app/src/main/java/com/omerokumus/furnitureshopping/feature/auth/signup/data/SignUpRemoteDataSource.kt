package com.omerokumus.furnitureshopping.feature.auth.signup.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.omerokumus.furnitureshopping.feature.auth.UserApi
import com.omerokumus.furnitureshopping.feature.auth.signup.data.model.UserRequest
import retrofit2.Retrofit
import javax.inject.Inject

class SignUpRemoteDataSource@Inject constructor(private val retrofit: Retrofit) {
    val api = retrofit.create(UserApi::class.java)

    private val auth: FirebaseAuth = Firebase.auth

    suspend fun addUser(userRequest: UserRequest) = api.addUser(userRequest)

    suspend fun createUser(email: String, password: String) = auth.createUserWithEmailAndPassword(email, password)
}