package com.example.furnitureshopping.extensions

import android.widget.TextView
import androidx.navigation.findNavController

enum class NavigationType {
    Activity, Fragment, Dialog
}

fun TextView?.asGoBack(navigationType: NavigationType = NavigationType.Fragment) {
    this?.let { view ->
        when (navigationType) {
            NavigationType.Activity -> {
                view.setOnClickListener { view.findNavController().navigateUp() }
            }
            NavigationType.Fragment -> {
                view.setOnClickListener { view.findNavController().navigateUp() }
            }
            NavigationType.Dialog -> {
                view.setOnClickListener { view.findNavController().navigateUp() }
            }
        }
    }
}