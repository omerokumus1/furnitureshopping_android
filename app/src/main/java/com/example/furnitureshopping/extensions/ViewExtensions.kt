package com.example.furnitureshopping.extensions

import android.view.View
import androidx.navigation.findNavController
import com.example.furnitureshopping.feature.auth.LoginFragmentDirections

fun View?.asSignUpButton() {
    this?.setOnClickListener {
        val direction = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        findNavController().navigateAnim(direction)
    }
}

fun View?.asForgotPasswordButton() {
    this?.setOnClickListener {
        val direction = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigateAnim(direction)
    }
}