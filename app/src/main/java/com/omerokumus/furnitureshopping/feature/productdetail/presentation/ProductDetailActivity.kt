package com.omerokumus.furnitureshopping.feature.productdetail.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.databinding.ActivityProductDetailBinding
import com.omerokumus.furnitureshopping.extensions.setBackgroundColorIfColorFormatCorrect
import com.omerokumus.furnitureshopping.extensions.setBlockingClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
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
        viewModel.getProductById(intent.getIntExtra("id", 1))
        setBackButton()
        initProductImgPager()
        setBookmarkClickListener()
    }

    private fun setBookmarkClickListener() {
        binding.bookmarkBtn.setBlockingClickListener {
            if (viewModel.productDetailLiveData.value?.isInFavoriteProducts == true) {
                viewModel.setIsInFavoriteProducts(false)
                binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark)
                viewModel.removeFavoriteProduct(viewModel.productDetailLiveData.value?.id ?: -1)
            } else {
                viewModel.productDetailLiveData.value?.let {
                    viewModel.setIsInFavoriteProducts(true)
                    binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark_filled)
                    viewModel.addFavoriteProduct(it.id)
                }

            }
        }
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
            productImgPagerAdapter.productImgList = it.imageNames
            productImgPagerAdapter.notifyDataSetChanged()
            binding.apply {
                productName.text = it.name
                productPrice.text = root.resources.getString(
                    R.string.price_format,
                    String.format(getCurrentLocale(root.resources.configuration), "%.2f", it.price)
                )
                productDescription.text = it.description
            }
            if (it.isInFavoriteProducts) {
                binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark_filled)
            } else {
                binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark)
            }

            displayProductColors(it.colorCodes)
        }
    }

    private fun displayProductColors(colorCodes: List<String>) {
        if (colorCodes.size == 1) {
            binding.run {
                firstColor.setBackgroundColorIfColorFormatCorrect(colorCodes[0])
                secondColorCardView.visibility = View.INVISIBLE
                thirdColorCardView.visibility = View.INVISIBLE
            }
        } else if (colorCodes.size == 2) {
            binding.run {
                firstColor.setBackgroundColorIfColorFormatCorrect(colorCodes[0])
                secondColor.setBackgroundColorIfColorFormatCorrect(colorCodes[1])
                thirdColorCardView.visibility = View.INVISIBLE
            }
        } else if (colorCodes.size == 3) {
            binding.run {
                firstColor.setBackgroundColorIfColorFormatCorrect(colorCodes[0])
                secondColor.setBackgroundColorIfColorFormatCorrect(colorCodes[1])
                thirdColor.setBackgroundColorIfColorFormatCorrect(colorCodes[2])
            }
        }
    }



    private fun getCurrentLocale(configuration: Configuration): Locale {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            configuration.locales.get(0)
        } else {
            configuration.locales.get(0)
        }
    }
}