package com.omerokumus.furnitureshopping.feature.productdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.main.bookmarks.presentation.model.BookmarkItem
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

    private val isProductBookmarkedMutableLiveData = MutableLiveData<Boolean>()
    val isProductBookmarkedLiveData: LiveData<Boolean> = isProductBookmarkedMutableLiveData


    fun getProductById(id: Int) {
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
        productDetailMutableLiveData.value?.let {
            userManager.addFavoriteProduct(BookmarkItem.from(it))
        }
    }

    fun removeFavoriteProduct(userId: Int, productId: Int) {
        viewModelScope.launch {
            repository.removeFavoriteProduct(userId, productId)
        }
        productDetailMutableLiveData.value?.let {
            userManager.removeFavoriteProduct(BookmarkItem.from(it))
        }
    }

    fun isProductBookmarked(productId: Int) {
        val isBookmarked = userManager.getUserFavoriteProducts().find { it.id == productId }
        isProductBookmarkedMutableLiveData.postValue(isBookmarked != null)
    }

    fun setIsProductBookmarked(isBookmarked: Boolean) {
        isProductBookmarkedMutableLiveData.postValue(isBookmarked)
    }
}