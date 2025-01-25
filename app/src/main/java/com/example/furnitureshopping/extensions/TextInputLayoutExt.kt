package com.example.furnitureshopping.extensions

import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout?.asEmailInput() {
    this?.editText?.doOnTextChanged { text, _, _, _ ->
        this.error = if (!"$text".isValidEmail()) "Email is required" else null
    }
}

fun TextInputLayout?.asPasswordInput() {
    this?.editText?.doOnTextChanged { text, _, _, _ ->
        this.error = if (!"$text".isValidPassword()) "Password is required" else null
    }
}