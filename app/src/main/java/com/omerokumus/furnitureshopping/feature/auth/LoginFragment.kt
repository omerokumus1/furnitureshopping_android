package com.omerokumus.furnitureshopping.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omerokumus.furnitureshopping.databinding.FragmentLoginBinding
import com.omerokumus.furnitureshopping.extensions.asEmailInput
import com.omerokumus.furnitureshopping.extensions.asForgotPasswordButton
import com.omerokumus.furnitureshopping.extensions.asLoginButton
import com.omerokumus.furnitureshopping.extensions.asPasswordInput
import com.omerokumus.furnitureshopping.extensions.asSignUpButton

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            emailTextField.asEmailInput()
            passwordTextField.asPasswordInput()
            signUpButton.asSignUpButton()
            forgotPasswordButton.asForgotPasswordButton()
            loginButton.asLoginButton()
        }
    }
}