package com.omerokumus.furnitureshopping.feature.productdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.productdetail.presentation.model.ProductDetail
import com.omerokumus.furnitureshopping.feature.productdetail.data.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(val repository: ProductDetailRepository) :
    ViewModel() {

    private val productDetailMutableLiveData = MutableLiveData<ProductDetail>()
    val productDetailLiveData: LiveData<ProductDetail> = productDetailMutableLiveData

    private val isProductBookmarkedMutableLiveData = MutableLiveData<Boolean>()
    val isProductBookmarkedLiveData: LiveData<Boolean> = isProductBookmarkedMutableLiveData


    fun getProductDetail(id: Int) {
        viewModelScope.launch {
            val response = repository.getProductById(id)
            if (response.isSuccessful && response.body() != null) {
                productDetailMutableLiveData.postValue(ProductDetail.from(response.body()))
            }
        }
    }

    fun addFavoriteProduct(userId: Int, productId: Int) {
        viewModelScope.launch {
            repository.addFavoriteProduct(userId, productId)
        }

    }

    fun removeFavoriteProduct(userId: Int, productId: Int) {
        viewModelScope.launch {
            repository.removeFavoriteProduct(userId, productId)
        }

    }

    fun isProductBookmarked(userId: Int, productId: Int) {
        viewModelScope.launch {
            val response = repository.getFavoriteProducts(userId)
            if (response.isSuccessful && response.body() != null) {
                val favoriteProducts = response.body()
                for (product in favoriteProducts!!) {
                    if (product.id == productId) {
                        setIsProductBookmarked(true)
                        break
                    }
                }
            } else {
                setIsProductBookmarked(false)
            }
        }
    }

    fun setIsProductBookmarked(isBookmarked: Boolean) {
        isProductBookmarkedMutableLiveData.postValue(isBookmarked)
    }
}