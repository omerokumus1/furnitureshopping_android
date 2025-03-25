package com.omerokumus.furnitureshopping.feature.productdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.productdetail.presentation.model.ProductDetail
import com.omerokumus.furnitureshopping.feature.productdetail.data.ProductDetailRepository
import com.omerokumus.furnitureshopping.feature.usermanager.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductDetailRepository,
    private val userManager: UserManager
) :
    ViewModel() {

    private val productDetailMutableLiveData = MutableLiveData<ProductDetail>()
    val productDetailLiveData: LiveData<ProductDetail> = productDetailMutableLiveData

    private val userId = userManager.getUser()?.id?.toInt()


    fun getProductById(productId: Int) {
        userId?.let {
            viewModelScope.launch {
                val response = repository.getProductById(productId, userId)
                if (response.isSuccessful && response.body() != null) {
                    productDetailMutableLiveData.postValue(ProductDetail.from(response.body()))
                }
            }
        }

    }

    fun addFavoriteProduct(productId: Int) {
        userId?.let {
            viewModelScope.launch {
                repository.addFavoriteProduct(userId, productId)
            }
        }
    }

    fun removeFavoriteProduct(productId: Int) {
        val userId = userManager.getUser()?.id?.toInt()
        userId?.let {
            viewModelScope.launch {
                repository.removeFavoriteProduct(userId, productId)
            }
        }
    }

    fun setIsInFavoriteProducts(isInFavoriteProducts: Boolean) {
        productDetailMutableLiveData.value?.isInFavoriteProducts = isInFavoriteProducts
    }
}