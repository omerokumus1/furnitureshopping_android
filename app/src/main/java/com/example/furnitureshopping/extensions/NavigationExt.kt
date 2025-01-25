package com.example.furnitureshopping.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.furnitureshopping.R

fun NavController?.navigateAnim(directions: NavDirections) {
    this?.navigate(directions,
        NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.slide_out)
            .build()
    )
}