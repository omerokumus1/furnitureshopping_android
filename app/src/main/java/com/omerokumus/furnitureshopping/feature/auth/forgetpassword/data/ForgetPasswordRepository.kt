package com.omerokumus.furnitureshopping.feature.auth.forgetpassword.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForgetPasswordRepository @Inject constructor(private val remoteDataSource: ForgetPasswordRemoteDataSource) {

    suspend fun getUserByEmail(email:String) = withContext(Dispatchers.IO){
        remoteDataSource.getUserByEmail(email)
    }

    suspend fun resetPassword(email: String) = withContext(Dispatchers.IO){
        remoteDataSource.resetPassword(email)
    }

}