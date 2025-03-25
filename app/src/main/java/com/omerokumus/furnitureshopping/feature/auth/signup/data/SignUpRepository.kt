package com.omerokumus.furnitureshopping.feature.auth.signup.data

import com.omerokumus.furnitureshopping.feature.auth.signup.data.model.UserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val remoteDataSource: SignUpRemoteDataSource) {


    suspend fun addUser(userRequest: UserRequest) = remoteDataSource.addUser(userRequest)

    suspend fun createUser(email: String, password: String) = withContext(Dispatchers.IO){
        remoteDataSource.createUser(email, password)
    }
}