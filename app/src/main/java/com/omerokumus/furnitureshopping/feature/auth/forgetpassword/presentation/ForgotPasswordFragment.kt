package com.omerokumus.furnitureshopping.feature.auth.forgetpassword.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omerokumus.furnitureshopping.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    private val viewModel: ForgetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForgotPasswordBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        binding.sendResetLinkButton.setOnClickListener {
            viewModel.getUserByEmail(binding.emailTextFieldEditText.text.toString())
        }
    }

    private fun observeViewModel(){
        observeIsResetLinkSent()
        observeIsUserExisting()
    }

    private fun observeIsResetLinkSent() {
        viewModel.isResetLinkSent.observe(viewLifecycleOwner){
            if (it){
                Toast.makeText(requireContext(),"Reset link sent to your email.",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"An error occurred, Please try again.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeIsUserExisting() {
        viewModel.isUserExisting.observe(viewLifecycleOwner){
            if (it){
                viewModel.sendResetLink(binding.emailTextFieldEditText.text.toString())
            }else{
                Toast.makeText(requireContext(),"There is no user with this email.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}