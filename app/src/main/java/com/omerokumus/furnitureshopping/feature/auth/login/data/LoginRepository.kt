package com.omerokumus.furnitureshopping.feature.auth.login.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(private val remoteDataSource: LoginRemoteDataSource) {

    suspend fun getUserById(userId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.getUserById(userId)
    }

    suspend fun getUserByEmail(email: String) = withContext(Dispatchers.IO) {
        remoteDataSource.getUserByEmail(email)
    }

    suspend fun checkFirebaseAuthentication(email: String, password: String) = withContext(Dispatchers.IO) {
        remoteDataSource.checkFirebaseAuthentication(email, password)
    }

    suspend fun checkLoggedInUser() = withContext(Dispatchers.IO) {
        remoteDataSource.checkLoggedInUser()
    }
}