package com.omerokumus.furnitureshopping.base

import androidx.fragment.app.Fragment

abstract class FurnitureBaseFragment : Fragment() {

    val furnitureBaseActivity: FurnitureBaseActivity by lazy {
        requireActivity() as FurnitureBaseActivity
    }
}