package com.example.furnitureshopping.extensions

fun String?.isValidEmail(): Boolean {
    return this?.let { android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() } ?: false
}

fun String?.isValidPassword(): Boolean {
    return (this?.length ?: 0) >= 8
}

fun onClickLogin(email: String?, password: String?) = email.isValidEmail() && password.isValidPassword()

