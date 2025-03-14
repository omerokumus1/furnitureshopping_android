package com.omerokumus.furnitureshopping.feature.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseActivity
import com.omerokumus.furnitureshopping.base.data.ToolbarLeftIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData
import com.omerokumus.furnitureshopping.databinding.ActivityMainBinding

class MainActivity : FurnitureBaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 80)
            insets
        }
        initBottomNavigationView()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        binding.bottomNav.bottomNav.setupWithNavController(navHostFragment.navController)

    }

    private fun initBottomNavigationView() {
        binding.bottomNav.bottomNav.run {
            setOnApplyWindowInsetsListener { view, insets ->
                view.updatePadding(bottom = 0)
                insets
            }
        }
    }

    override fun setToolbarLeftIconData(leftIconData: ToolbarLeftIconData) {
        binding.toolbar.leftIcon.run {
            leftIconData.iconResId?.let { setImageResource(it) }
            visibility = leftIconData.visibility
            contentDescription = leftIconData.contentDescription
            setOnClickListener { leftIconData.onClick?.invoke() }
        }
    }

    override fun setToolbarRightIconData(rightIconData: ToolbarRightIconData) {
        binding.toolbar.rightIcon.run {
            rightIconData.iconResId?.let { setImageResource(it) }
            visibility = rightIconData.visibility
            contentDescription = rightIconData.contentDescription
            setOnClickListener { rightIconData.onClick?.invoke() }
        }
    }

    override fun setToolbarTitleData(titleData: ToolbarTitleData) {
        binding.toolbar.title.run {
            titleData.title?.let { text = it }
            visibility = titleData.visibility
        }
    }

    override fun setToolbarSubTitleData(subTitleData: ToolbarSubTitleData) {
        binding.toolbar.subtitle.run {
            subTitleData.subTitle?.let { text = it }
            visibility = subTitleData.visibility
        }
    }

}