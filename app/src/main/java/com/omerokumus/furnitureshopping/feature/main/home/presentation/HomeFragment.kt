package com.omerokumus.furnitureshopping.feature.main.home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseFragment
import com.omerokumus.furnitureshopping.base.data.ToolbarLeftIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData
import com.omerokumus.furnitureshopping.databinding.FragmentHomeBinding
import com.omerokumus.furnitureshopping.feature.main.home.presentation.model.CategoryItem
import com.omerokumus.furnitureshopping.feature.productdetail.ProductDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    private val viewModel: HomeFragmentViewModel by viewModels()
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
        setCategoryAdapter()
        setProductAdapter()
        setRecyclerViewAdapters()
        observeViewModel()
        viewModel.fetchProducts()
    }

    private fun setRecyclerViewAdapters() {
        binding.run {
            categoryList.adapter = categoryAdapter
            productGrid.adapter = productAdapter
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            networkState.observe(viewLifecycleOwner) {
                when (it) {
                    HomeFragmentViewModel.NetworkState.LOADING -> handleLoadingState()
                    HomeFragmentViewModel.NetworkState.ERROR -> handleErrorState()
                    HomeFragmentViewModel.NetworkState.SUCCESS -> handleSuccessState()
                }
            }
            productsLiveData.observe(viewLifecycleOwner) { products ->
                if (products.isEmpty()) {
                    binding.run {
                        categoryList.visibility = View.GONE
                        productGrid.visibility = View.GONE
                        noProductsView.root.visibility = View.VISIBLE
                    }
                } else {
                    productAdapter.submitList(products)
                }
            }
        }
    }

    private fun handleSuccessState() {
        binding.run {
            progressIndicator.visibility = View.GONE
            categoryList.visibility = View.VISIBLE
            productGrid.visibility = View.VISIBLE
        }
    }

    private fun handleErrorState() {
        binding.run {
            progressIndicator.visibility = View.GONE
            categoryList.visibility = View.GONE
            productGrid.visibility = View.GONE
            AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                .setTitle(R.string.network_error_title)
                .setMessage(R.string.products_network_error_message)
                .setPositiveButton(R.string.try_again) { dialog, _ ->
                    viewModel.fetchProducts()
                    dialog.dismiss()
                }.setNeutralButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun handleLoadingState() {
        binding.run {
            progressIndicator.visibility = View.VISIBLE
            categoryList.visibility = View.GONE
            productGrid.visibility = View.GONE
        }
    }

    private fun setCategoryAdapter() {
        categoryAdapter = CategoryItemAdapter(categories) { categoryItem ->
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

    private fun setProductAdapter() {
        productAdapter = ProductItemAdapter(emptyList()) { productItem ->
            Intent(requireContext(), ProductDetailActivity::class.java).apply {
                putExtra("id", productItem.id)
                startActivity(this)
            }
        }
    }

    private fun initToolbar() {
        furnitureBaseActivity.run {
            setToolbarTitleData(ToolbarTitleData(title = requireContext().getString(R.string.home_toolbar_title)))
            setToolbarSubTitleData(ToolbarSubTitleData(subTitle = requireContext().getString(R.string.home_toolbar_subtitle)))
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

}