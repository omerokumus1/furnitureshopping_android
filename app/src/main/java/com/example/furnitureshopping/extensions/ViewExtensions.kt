package com.example.furnitureshopping.extensions

import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import com.example.furnitureshopping.feature.auth.LoginFragmentDirections

fun View?.asSignUpButton() {
    this?.setOnClickListener {
        val direction = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        findNavController().navigateAnim(direction)
    }
}