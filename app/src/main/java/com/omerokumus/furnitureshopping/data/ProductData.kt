package com.omerokumus.furnitureshopping.data

import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.feature.main.home.ProductItem
import com.omerokumus.furnitureshopping.feature.productdetail.ProductDetail

object ProductData {
    val productDetailData = listOf(
        ProductDetail(
            1,
            "Black Simple Lamp",
            12.00,
            listOf(R.drawable.lamp, R.drawable.lamp, R.drawable.lamp),
            "Black Simple Lamp is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        ),
        ProductDetail(
            2,
            "Minimal Stand",
            25.00,
            listOf(R.drawable.stand, R.drawable.stand, R.drawable.stand),
            "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        ),
        ProductDetail(
            3,
            "Coffee Chair",
            20.00,
            listOf(R.drawable.chair, R.drawable.chair),
            "Coffee Chair is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        ),
        ProductDetail(
            4,
            "Simple Desk",
            50.00,
            listOf(R.drawable.desk, R.drawable.desk, R.drawable.desk, R.drawable.desk),
            "Simple Desk is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
        )
    )
    val productItemData = listOf(
        ProductItem(
            1,
            "Black Simple Lamp",
            12.00,
            R.drawable.lamp,
        ),
        ProductItem(
            2,
            "Minimal Stand",
            25.00,
            R.drawable.stand,
        ),
        ProductItem(
            3,
            "Coffee Chair",
            20.00,
            R.drawable.chair,
        ),
        ProductItem(
            4,
            "Simple Desk",
            50.00,
            R.drawable.desk,
        )
    )
}