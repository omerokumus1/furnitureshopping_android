package com.omerokumus.furnitureshopping.feature.auth.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.auth.login.data.LoginRepository
import com.omerokumus.furnitureshopping.feature.usermanager.UserManager
import com.omerokumus.furnitureshopping.feature.usermanager.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val userManager: UserManager
) : ViewModel() {


    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    private val _isThereLoggedInUser = MutableLiveData<Boolean>()
    val isThereLoggedInUser: LiveData<Boolean> = _isThereLoggedInUser

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            val response = repository.getUserById(userId)
            if (response.isSuccessful && response.body() != null) {
                setUser(response.body()!!)
            }
        }
    }

    private fun getUserByEmail(email: String) {
        viewModelScope.launch {
            val response = repository.getUserByEmail(email)
            if (response.isSuccessful && response.body() != null) {
                setUser(response.body()!!)
            }
        }
    }

    private fun setUser(user: User) {
        userManager.setUser(user)
    }


    fun checkFirebaseAuthentication(email: String, password: String) {
        viewModelScope.launch {
            repository.checkFirebaseAuthentication(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    getUserByEmail(email)
                }
                _isLoginSuccessful.value = it.isSuccessful
            }
        }
    }

    fun isLoginAllowed(emailError: String?, passwordError: String?): Boolean {
        return emailError != null && passwordError != null
    }

    fun checkLoggedInUser(){
        viewModelScope.launch {
            _isLoginSuccessful.value = repository.checkLoggedInUser()
        }
    }

}