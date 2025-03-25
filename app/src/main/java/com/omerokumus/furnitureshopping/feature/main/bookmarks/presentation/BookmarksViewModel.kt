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


    fun getBookmarks() {
        val userId = userManager.getUser()?.id?.toInt()
        userId?.let {
            viewModelScope.launch {
                val response = repository.getFavoriteProducts(userId)
                if (response.isSuccessful) {
                    response.body()
                        ?.mapNotNull { BookmarkItem.from(it) }
                        ?.let {
                            _bookmarks.postValue(it)
                        }
                        ?: _bookmarks.postValue(emptyList())

                } else {
                    _bookmarks.postValue(emptyList())
                }
        }

        }
    }

    fun removeFromBookmarks(productId: Int) {
        val userId = userManager.getUser()?.id?.toInt()
        userId?.let {
            viewModelScope.launch {
                repository.removeFavoriteProduct(userId, productId)
            }
        }
    }
}
