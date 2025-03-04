package com.omerokumus.furnitureshopping.feature.productdetail

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.data.BookmarkData
import com.omerokumus.furnitureshopping.databinding.ActivityProductDetailBinding
import com.omerokumus.furnitureshopping.feature.main.bookmarks.BookmarkItem
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
        setBookmarkClickListener()
    }

    private fun setBookmarkClickListener() {
        binding.bookmarkBtn.setOnClickListener {
            if (isAlreadyBookmarked()) {
                BookmarkData.bookmarkData.removeIf { bookmarkItem -> bookmarkItem.productId == viewModel.productDetailLiveData.value?.id }
                binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark)
            } else {
                viewModel.productDetailLiveData.value?.let {
                    BookmarkData.bookmarkData.add(
                        BookmarkItem(
                            BookmarkData.bookmarkData.size,
                            it.id,
                            it.name,
                            it.images.first(),
                            it.price
                        )
                    )
                    binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark_filled)
                }
            }
        }
    }

    private fun isAlreadyBookmarked() = BookmarkData.bookmarkData.find { bookmarkItem ->
        bookmarkItem.productId == viewModel.productDetailLiveData.value?.id
    } != null

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
                productPrice.text = root.resources.getString(
                    R.string.price_format,
                    String.format(getCurrentLocale(root.resources.configuration), "%.2f", it.price)
                )
                productDescription.text = it.description
                BookmarkData.bookmarkData.find { bookmarkItem -> bookmarkItem.productId == it.id }
                    ?.let {
                        binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark_filled)
                    }
            }
        }
    }

    private fun getCurrentLocale(configuration: Configuration): Locale {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            configuration.locales.get(0)
        } else {
            configuration.locale
        }
    }
}