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

    private val userMutableLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = userMutableLiveData

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            val response = repository.getUserById(userId)
            if (response.isSuccessful && response.body() != null) {
                userMutableLiveData.postValue(response.body()!!)
            }
        }
    }

    fun setUser(user: User) {
        userManager.setUser(user)
    }

}