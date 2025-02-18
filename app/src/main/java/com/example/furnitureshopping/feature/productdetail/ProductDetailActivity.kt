package com.example.furnitureshopping.feature.productdetail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.furnitureshopping.databinding.ActivityProductDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()

    private val productImgPagerAdapter = ProductImgPagerAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeViewModel()
        viewModel.getImgList(intent.getIntExtra("id", 1))
        initProductImgPager()
    }

    private fun initProductImgPager() {
        binding.apply {
            productImgPager.adapter = productImgPagerAdapter
            TabLayoutMediator(
                productImgTabLayout,
                productImgPager
            ) { tab, position -> }.attach()

            // TODO Animation not working
            productImgTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.view?.animate()?.scaleX(1.2f)?.scaleY(1.2f)?.setDuration(200)?.start()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.view?.animate()?.scaleX(1.0f)?.scaleY(1.0f)?.setDuration(200)?.start()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // No change needed on reselection
                }
            })

            productImgPager.setPageTransformer(MarginPageTransformer(8))

        }
    }

    private fun observeViewModel() {
        viewModel.imgListLiveData.observe(this) {
            productImgPagerAdapter.productImgList = it
            productImgPagerAdapter.notifyDataSetChanged()
        }
    }
}