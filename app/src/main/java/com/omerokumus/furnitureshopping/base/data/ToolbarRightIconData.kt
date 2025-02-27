package com.omerokumus.furnitureshopping.base.data

import android.view.View
import androidx.annotation.DrawableRes

data class ToolbarRightIconData(
    @DrawableRes val iconResId: Int? = null,
    val visibility: Int = View.GONE,
    val contentDescription: String,
    val onClick: (() -> Unit)? = null
)

data class ToolbarTitleData(
    val title: String? = null,
    val visibility: Int = View.VISIBLE
)

data class ToolbarSubTitleData(
    val subTitle: String? = null,
    val visibility: Int = View.VISIBLE
)

data class ToolbarLeftIconData(
    @DrawableRes val iconResId: Int? = null,
    val visibility: Int = View.GONE,
    val contentDescription: String,
    val onClick: (() -> Unit)? = null
) {
}

