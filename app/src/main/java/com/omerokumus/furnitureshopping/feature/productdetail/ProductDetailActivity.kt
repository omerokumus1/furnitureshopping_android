package com.omerokumus.furnitureshopping.feature.productdetail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import com.omerokumus.furnitureshopping.databinding.ActivityProductDetailBinding
import java.util.Locale

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
        viewModel.getProductDetail(intent.getIntExtra("id", 1))
        setBackButton()
        initProductImgPager()
    }

    private fun setBackButton() {
        binding.backBtnContainer.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initProductImgPager() {
        binding.apply {
            productImgPager.adapter = productImgPagerAdapter
            TabLayoutMediator(
                productImgTabLayout,
                productImgPager
            ) { tab, position -> }.attach()
            productImgPager.setPageTransformer(MarginPageTransformer(8))

        }
    }

    private fun observeViewModel() {
        viewModel.productDetailLiveData.observe(this) {
            productImgPagerAdapter.productImgList = it.images
            productImgPagerAdapter.notifyDataSetChanged()
            binding.apply {
                productName.text = it.name
                productPrice.text = String.format(Locale.ENGLISH, "$%.2f", it.price)
                productDescription.text = it.description
            }
        }
    }
}