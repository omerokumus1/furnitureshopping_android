package com.omerokumus.furnitureshopping.feature.main.bookmarks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerokumus.furnitureshopping.feature.main.bookmarks.data.BookmarkRepository
import com.omerokumus.furnitureshopping.feature.main.bookmarks.presentation.model.BookmarkItem
import com.omerokumus.furnitureshopping.feature.usermanager.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: BookmarkRepository,
    private val userManager: UserManager
) :
    ViewModel() {
    private val _bookmarks: MutableLiveData<List<BookmarkItem>> = MutableLiveData()
    val bookmarksLiveData: LiveData<List<BookmarkItem>> = _bookmarks

    init {
        if (userManager.getUserFavoriteProducts().isNotEmpty()){
            _bookmarks.value = userManager.getUserFavoriteProducts()
        }else{
            getBookmarks(userManager.getUser()?.id?.toInt() ?: -1)

        }
    }

    private fun getBookmarks(userId: Int) {
        viewModelScope.launch {
            val response = repository.getFavoriteProducts(userId)
            if (response.isSuccessful) {
                response.body()
                    ?.mapNotNull { BookmarkItem.from(it) }
                    ?.let {
                        _bookmarks.postValue(it)
                        userManager.setUserFavoriteProducts(it) }
                    ?: _bookmarks.postValue(emptyList())

            } else {
                _bookmarks.postValue(emptyList())
            }
        }
    }

    fun removeFromBookmarks(userId: Int, productId: Int) {
        userManager.removeFavoriteProductById(productId)
        viewModelScope.launch {
            repository.removeFavoriteProduct(userId, productId)
        }
    }

    fun setBookmarksFromUserManager(){
        _bookmarks.value = userManager.getUserFavoriteProducts()

    }
}
