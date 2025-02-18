package com.example.furnitureshopping.feature.productdetail

class ProductDetailRepository {
    private val dataSource = ProductDetailRemoteDataSource()

    fun getDrawerImgList() = dataSource.drawerImgList

    fun getDrawer2ImgList() = dataSource.drawer2ImgList

    fun getChairImgList() = dataSource.chairImgList

    fun getLampImgList() = dataSource.lampImgList


}