package com.omerokumus.furnitureshopping.feature.main.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.main.home.data.ProductsRepository
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    // TODO omer change to liveData Coroutine builder
    private val _products: MutableLiveData<List<ProductItem>> = MutableLiveData()
    val productsLiveData: LiveData<List<ProductItem>> = _products

    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: LiveData<NetworkState> = _networkState

    fun fetchProducts() {
        viewModelScope.launch {
            _networkState.postValue(NetworkState.LOADING)
            val response = productsRepository.getProducts()
            if (response.isSuccessful) {
                _networkState.postValue(NetworkState.SUCCESS)
                response.body()
                    ?.mapNotNull { ProductItem.from(it) }
                    ?.let { _products.postValue(it) }
                    ?: _products.postValue(emptyList())
            } else {
                _networkState.postValue(NetworkState.ERROR)
            }
        }
    }

    // TODO omer change to sealed class with data inside
    enum class NetworkState {
        LOADING,
        SUCCESS,
        ERROR
    }
}