package com.omerokumus.furnitureshopping.feature.main.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.ProductItem
import com.omerokumus.furnitureshopping.feature.productdetail.data.ProductDetailRepository
import com.omerokumus.furnitureshopping.feature.usermanager.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(private val repository: ProductDetailRepository,
    private val userManager: UserManager) :
    ViewModel() {
    private val _bookmarks: MutableLiveData<List<ProductItem>> = MutableLiveData()
    val bookmarksLiveData: LiveData<List<ProductItem>> = _bookmarks

    fun getBookmarks(userId: Int) {
        viewModelScope.launch {
            val response = repository.getFavoriteProducts(userId)
            if (response.isSuccessful){
                response.body()
                    ?.mapNotNull { ProductItem.from(it) }
                    ?.let { _bookmarks.postValue(it) }
                    ?: _bookmarks.postValue(emptyList())
            }else{
                _bookmarks.postValue(emptyList())
            }
        }
    }

    fun removeFromBookmarks(userId: Int, productId: Int){
        userManager.removeFavoriteProductById(productId)
        viewModelScope.launch {
            repository.removeFavoriteProduct(userId, productId)
        }
    }
}
