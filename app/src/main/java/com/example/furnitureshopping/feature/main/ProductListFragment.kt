package com.example.furnitureshopping.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.furnitureshopping.R
import com.example.furnitureshopping.databinding.FragmentHomeBinding
import com.example.furnitureshopping.feature.productdetail.ProductDetailActivity

class ProductListFragment : Fragment() {
    private var products = listOf(
        ProductItem(
            1,
            "Black Simple Lamp",
            12.00,
            R.drawable.lamb,
            {}
        ),
        ProductItem(
            2,
            "Minimal Stand",
            25.00,
            R.drawable.drawer,
            {}
        ),
        ProductItem(
            3,
            "Coffee Chair",
            20.00,
            R.drawable.chair,
            {}
        ),
        ProductItem(
            4,
            "Simple Desk",
            50.00,
            R.drawable.drawer2,
            {}
        )
    )
    private var categories = listOf(
        CategoryItem(
            id = 1,
            categoryIcon = R.drawable.ic_star,
            name = "Popular",
            isClicked = true,
            onClick = {}
        ),
        CategoryItem(
            id = 2,
            categoryIcon = R.drawable.ic_chair,
            name = "Chair",
            onClick = {}
        ),
        CategoryItem(
            id = 3,
            categoryIcon = R.drawable.ic_table,
            name = "Table",
            onClick = {}
        ),
        CategoryItem(
            id = 4,
            categoryIcon = R.drawable.ic_sofa,
            name = "Armchair",
            onClick = {}
        ),
        CategoryItem(
            5,
            categoryIcon = R.drawable.ic_bed,
            name = "Bed",
            onClick = {}
        ),
        CategoryItem(
            6,
            categoryIcon = R.drawable.ic_lamb,
            name = "Lamp",
            onClick = {}
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
        categoryAdapter = getCategoryItemAdapter()

        productAdapter = ProductItemAdapter(products) { productItem ->
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

    private fun getCategoryItemAdapter() = CategoryItemAdapter(categories) { categoryItem ->
        val previousClicked = categories.find { it.isClicked }
        categories = categories.map {
            if (it.id == previousClicked?.id) it.copy(isClicked = false)
            else it.copy(isClicked = it.id == categoryItem.id)
        }
        categoryAdapter.values = categories
        categoryAdapter.notifyDataSetChanged()
    }
}