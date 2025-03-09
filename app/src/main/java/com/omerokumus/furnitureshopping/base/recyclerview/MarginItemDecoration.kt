package com.omerokumus.furnitureshopping.base.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    @Px private val top: Int? = null,
    @Px private val bottom: Int? = null,
    @Px private val start: Int? = null,
    @Px private val end: Int? = null,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        top?.let { outRect.top = it }
        bottom?.let { outRect.bottom = it }
        start?.let { outRect.left = it }
        end?.let { outRect.right = it }
    }
}