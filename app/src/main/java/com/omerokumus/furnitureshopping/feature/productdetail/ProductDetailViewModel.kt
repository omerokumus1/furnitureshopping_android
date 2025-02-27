package com.omerokumus.furnitureshopping.feature.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductDetailViewModel : ViewModel() {
    private val repository = ProductDetailRepository()
    private val productDetailMutableLiveData = MutableLiveData<ProductDetail>()
    val productDetailLiveData: LiveData<ProductDetail> = productDetailMutableLiveData

    fun getProductDetail(id: Int) {
        productDetailMutableLiveData.value = when (id) {
            1 -> repository.getLampDetail()
            2 -> repository.getDrawerDetail()
            3 -> repository.getChairDetail()
            4 -> repository.getDrawer2Detail()
            else -> null
        }
    }


}