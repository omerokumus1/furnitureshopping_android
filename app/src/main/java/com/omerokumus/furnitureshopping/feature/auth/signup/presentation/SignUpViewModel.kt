package com.omerokumus.furnitureshopping.feature.auth.signup.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.auth.signup.data.SignUpRepository
import com.omerokumus.furnitureshopping.feature.auth.signup.data.model.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: SignUpRepository) : ViewModel() {

    private val _doesUserCreated = MutableLiveData<Boolean>()
    val doesUserCreated: LiveData<Boolean> = _doesUserCreated



    fun createUser(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            repository.createUser(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    addUser(UserRequest(fullName, email))
                }
                _doesUserCreated.value = it.isSuccessful
            }
        }

    }

    private fun addUser(userRequest: UserRequest) {
        viewModelScope.launch {
            repository.addUser(userRequest)
            println("User added to repository")
        }
    }




    fun isRegisterAllowed(errorMessages: List<String?>): Boolean {
        for (error in errorMessages) {
            if (!error.equals("null"))
                return false
        }
        return true
    }

    fun checkIfFieldEmpty(field: String?): Boolean {
        return field.isNullOrEmpty()
    }
}