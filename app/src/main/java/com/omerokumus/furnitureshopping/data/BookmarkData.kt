package com.omerokumus.furnitureshopping.data

import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.feature.main.bookmarks.BookmarkItem

object BookmarkData {
    val bookmarkData = listOf(
        BookmarkItem(
            1,
            "Black Simple Lamp",
            R.drawable.lamp,
            12.00,
        ),
        BookmarkItem(
            2,
            "Minimal Stand",
            R.drawable.stand,
            25.00,
            true
        ),
    )
}