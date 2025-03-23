package com.omerokumus.furnitureshopping.feature.auth.login.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(private val remoteDataSource: LoginRemoteDataSource) {

    suspend fun getUserById(userId: Int) = withContext(Dispatchers.IO) {
        remoteDataSource.getUserById(userId)
    }
}