package com.example.furnitureshopping.feature.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.furnitureshopping.R
import com.example.furnitureshopping.databinding.FragmentSignUpBinding
import com.example.furnitureshopping.extensions.asConfirmPasswordInput
import com.example.furnitureshopping.extensions.asEmailInput
import com.example.furnitureshopping.extensions.asGoBack
import com.example.furnitureshopping.extensions.asNameInput
import com.example.furnitureshopping.extensions.asPasswordInput

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