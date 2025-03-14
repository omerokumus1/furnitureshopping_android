package com.example.furnitureshopping.feature.productdetail

class ProductDetailRepository {
    private val dataSource = ProductDetailRemoteDataSource()

    fun getDrawerDetail() = dataSource.drawerDetail

    fun getDrawer2Detail() = dataSource.drawer2Detail

    fun getChairDetail() = dataSource.chairDetail

    fun getLampDetail() = dataSource.lampDetail


}