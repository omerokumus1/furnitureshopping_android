package com.omerokumus.furnitureshopping.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omerokumus.furnitureshopping.databinding.FragmentSignUpBinding
import com.omerokumus.furnitureshopping.extensions.asConfirmPasswordInput
import com.omerokumus.furnitureshopping.extensions.asEmailInput
import com.omerokumus.furnitureshopping.extensions.asGoBack
import com.omerokumus.furnitureshopping.extensions.asNameInput
import com.omerokumus.furnitureshopping.extensions.asPasswordInput

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSignUpBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            emailTextField.asEmailInput()
            passwordTextField.asPasswordInput()
            confirmPasswordTextField.asConfirmPasswordInput(passwordTextField)
            nameTextField.asNameInput()
            signInButton.asGoBack()
        }

    }
}