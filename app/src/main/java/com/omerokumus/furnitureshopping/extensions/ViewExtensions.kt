package com.omerokumus.furnitureshopping.extensions

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import com.omerokumus.furnitureshopping.feature.auth.AuthActivity
import com.omerokumus.furnitureshopping.feature.auth.login.presentation.LoginFragmentDirections
import com.omerokumus.furnitureshopping.feature.main.MainActivity

/**
 * Navigate from Login to SignUp Fragment
 */
fun View?.asSignUpButton() {
    this?.setOnClickListener {
        val direction = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        findNavController().navigateAnim(direction)
    }
}

/**
 * Navigate from Login to Forgot Password Fragment
 */
fun View?.asForgotPasswordButton() {
    this?.setOnClickListener {
        val direction = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigateAnim(direction)
    }
}

/**
 * Navigate from Login to MainActivity
 */
fun View?.asLoginButton(isLoginSuccessful: () -> Boolean) {
    this?.setOnClickListener {
        if (isLoginSuccessful())
            Intent(context, MainActivity::class.java).also {
                context.startActivity(it)
                findActivity()?.finish()
            }
    }
}

/**
 * Navigate from Onboarding to AuthActivity
 */
fun View?.asGetStartedButton() {
    this?.setOnClickListener {
        Intent(context, AuthActivity::class.java).also {
            context.startActivity(it)
            findActivity()?.finish()
        }
    }
}

/**
 * Find hosting activity of the view
 */
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

fun View.setBlockingClickListener(onClick: () -> Unit) {
    this.setOnClickListener {
        this.isClickable = false
        onClick()
        this.postDelayed({ this.isClickable = true }, 1000)

    }
}