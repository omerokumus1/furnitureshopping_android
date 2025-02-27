package com.omerokumus.furnitureshopping.base

import androidx.appcompat.app.AppCompatActivity
import com.omerokumus.furnitureshopping.base.data.ToolbarLeftIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData

abstract class FurnitureBaseActivity : AppCompatActivity() {

    abstract fun setToolbarLeftIconData(leftIconData: ToolbarLeftIconData)

    abstract fun setToolbarRightIconData(rightIconData: ToolbarRightIconData)

    abstract fun setToolbarTitleData(titleData: ToolbarTitleData)

    abstract fun setToolbarSubTitleData(subTitleData: ToolbarSubTitleData)

}


