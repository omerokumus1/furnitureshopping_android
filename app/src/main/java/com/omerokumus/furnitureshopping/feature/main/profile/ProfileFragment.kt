package com.omerokumus.furnitureshopping.feature.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.FurnitureBaseFragment
import com.omerokumus.furnitureshopping.base.data.ToolbarRightIconData
import com.omerokumus.furnitureshopping.base.data.ToolbarSubTitleData
import com.omerokumus.furnitureshopping.base.data.ToolbarTitleData
import com.omerokumus.furnitureshopping.databinding.FragmentProfileBinding
import com.omerokumus.furnitureshopping.feature.auth.AuthActivity

class ProfileFragment : FurnitureBaseFragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProfileBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        setToolbarTitle()
        setToolbarSubtitle()
        setToolbarRightIcon()
    }


    private fun setToolbarTitle() {
        furnitureBaseActivity.setToolbarTitleData(
            ToolbarTitleData(
                title = null,
                visibility = View.GONE
            )
        )
    }

    private fun setToolbarSubtitle() {
        furnitureBaseActivity.setToolbarSubTitleData(
            ToolbarSubTitleData(
                subTitle = requireContext().getString(R.string.profile),
                visibility = View.VISIBLE
            )
        )
    }

    private fun setToolbarRightIcon() {
        furnitureBaseActivity.setToolbarRightIconData(ToolbarRightIconData(
            iconResId = R.drawable.ic_logout,
            visibility = View.VISIBLE,
            contentDescription = requireContext().getString(R.string.log_out),
            onClick = {
                Intent(requireContext(), AuthActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finish()
                }
            }
        ))
    }


}