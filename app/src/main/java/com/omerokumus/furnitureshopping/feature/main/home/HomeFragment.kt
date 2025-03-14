package com.omerokumus.furnitureshopping.feature.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseFragment
import com.omerokumus.furnitureshopping.base.data.ToolbarLeftIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData
import com.omerokumus.furnitureshopping.data.ProductData
import com.omerokumus.furnitureshopping.databinding.FragmentHomeBinding
import com.omerokumus.furnitureshopping.feature.productdetail.ProductDetailActivity

class HomeFragment : FurnitureBaseFragment() {
    private var categories = listOf(
        CategoryItem(
            id = 1,
            categoryIcon = R.drawable.ic_star,
            categoryIconSelected = R.drawable.ic_star_filled,
            name = "Popular",
            isClicked = true,
        ),
        CategoryItem(
            id = 2,
            categoryIcon = R.drawable.ic_chair,
            categoryIconSelected = R.drawable.ic_chair_filled,
            name = "Chair",
        ),
        CategoryItem(
            id = 3,
            categoryIcon = R.drawable.ic_table,
            categoryIconSelected = R.drawable.ic_table_filled,
            name = "Table",
        ),
        CategoryItem(
            id = 4,
            categoryIcon = R.drawable.ic_sofa,
            categoryIconSelected = R.drawable.ic_sofa_filled,
            name = "Armchair",
        ),
        CategoryItem(
            5,
            categoryIcon = R.drawable.ic_bed,
            categoryIconSelected = R.drawable.ic_bed_filled,
            name = "Bed",
        ),
        CategoryItem(
            6,
            categoryIcon = R.drawable.ic_lamp,
            categoryIconSelected = R.drawable.ic_lamp_filled,
            name = "Lamp",
        ),
    )

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryItemAdapter
    private lateinit var productAdapter: ProductItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        categoryAdapter = getCategoryItemAdapter()

        productAdapter = ProductItemAdapter(ProductData.productItemData) { productItem ->
            Intent(requireContext(), ProductDetailActivity::class.java).apply {
                putExtra("id", productItem.id)
                startActivity(this)
            }
        }

        binding.run {
            categoryList.adapter = categoryAdapter
            productGrid.adapter = productAdapter
        }
    }

    private fun initToolbar() {
        furnitureBaseActivity.run {
            setToolbarTitleData(ToolbarTitleData(title = "Make Home"))
            setToolbarSubTitleData(ToolbarSubTitleData(subTitle = "BEAUTIFUL"))
            setToolbarLeftIconData(
                ToolbarLeftIconData(
                    visibility = View.GONE,
                    contentDescription = ""
                )
            )
            setToolbarRightIconData(
                ToolbarRightIconData(
                    visibility = View.GONE,
                    contentDescription = ""
                )
            )
        }

    }

    private fun getCategoryItemAdapter() = CategoryItemAdapter(categories) { categoryItem ->
        val previousClicked = categories.find { it.isClicked }
        var prevIndex = 0
        var clickedIndex = 0
        categories = categories.mapIndexed { index, it ->
            if (it.id == previousClicked?.id) {
                it.copy(isClicked = false).also { prevIndex = index }
            } else it.copy(isClicked = it.id == categoryItem.id)
                .also { if (it.id == categoryItem.id) clickedIndex = index }
        }
        categoryAdapter.values = categories
        categoryAdapter.notifyItemChanged(prevIndex)
        categoryAdapter.notifyItemChanged(clickedIndex)
    }
}