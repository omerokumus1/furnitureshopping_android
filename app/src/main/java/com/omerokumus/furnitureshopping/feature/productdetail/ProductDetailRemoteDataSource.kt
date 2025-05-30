package com.omerokumus.furnitureshopping.feature.productdetail

import com.omerokumus.furnitureshopping.data.ProductData

class ProductDetailRemoteDataSource {

    val lampDetail = ProductData.productDetailData.find { it.id == 1 }

    val drawerDetail = ProductData.productDetailData.find { it.id == 2 }

    val chairDetail = ProductData.productDetailData.find { it.id == 3 }

    val drawer2Detail = ProductData.productDetailData.find { it.id == 4 }
}