package com.example.furnitureshopping.feature.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductDetailViewModel : ViewModel() {
    private val repository = ProductDetailRepository()
    private val imgListMutableLiveData = MutableLiveData<List<Int>>()
    val imgListLiveData: LiveData<List<Int>> = imgListMutableLiveData

    fun getImgList(id: Int) {
        imgListMutableLiveData.value = when (id) {
            1 -> repository.getLampImgList()
            2 -> repository.getDrawerImgList()
            3 -> repository.getChairImgList()
            4 -> repository.getDrawer2ImgList()
            else -> emptyList()
        }
    }


}