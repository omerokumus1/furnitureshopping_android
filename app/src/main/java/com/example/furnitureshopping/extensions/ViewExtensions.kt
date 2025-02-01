package com.example.furnitureshopping.extensions

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import com.example.furnitureshopping.feature.auth.LoginFragmentDirections
import com.example.furnitureshopping.feature.main.MainActivity

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

fun View?.asLoginButton() {
    this?.setOnClickListener {
        Intent(context, MainActivity::class.java).also {
            context.startActivity(it)
            findActivity()?.finish()
        }
    }
}

fun View.findActivity(): Activity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}