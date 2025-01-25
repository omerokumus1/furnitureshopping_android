package com.example.furnitureshopping.extensions

import android.widget.EditText
import androidx.core.widget.doOnTextChanged

fun EditText?.asEmailInput() {
    this?.doOnTextChanged { text, _, _, _ ->
        error = if (!"$text".isValidEmail()) "Email is required" else null
    }
}

fun EditText?.asPasswordInput() {
    this?.doOnTextChanged { text, _, _, _ ->
        error = if (!"$text".isValidPassword()) "Password is required" else null
    }
}

fun EditText?.asNameInput() {
    this?.doOnTextChanged { text, _, _, _ ->
        error = if (!"$text".isValidName()) "Name is required" else null
    }
}
