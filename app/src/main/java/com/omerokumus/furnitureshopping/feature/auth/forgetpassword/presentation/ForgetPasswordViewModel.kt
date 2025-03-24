package com.omerokumus.furnitureshopping.feature.auth.forgetpassword.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.auth.forgetpassword.data.ForgetPasswordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgetPasswordViewModel @Inject constructor(private val repository: ForgetPasswordRepository) :
    ViewModel() {

        private val _isUserExisting = MutableLiveData<Boolean>()
        val isUserExisting: LiveData<Boolean> = _isUserExisting

        private val _isResetLinkSent = MutableLiveData<Boolean>()
        val isResetLinkSent: LiveData<Boolean> = _isResetLinkSent

        fun getUserByEmail(email: String){
            viewModelScope.launch {
                val response = repository.getUserByEmail(email)
                if (response.isSuccessful && response.body() != null) {
                       _isUserExisting.postValue(true)
                }else{
                    _isUserExisting.postValue(false)
                }
            }
        }

        fun sendResetLink(email: String){
            viewModelScope.launch {
                repository.resetPassword(email).addOnCompleteListener{
                    _isResetLinkSent.postValue(it.isSuccessful)
                }
            }

        }

}