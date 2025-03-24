package com.omerokumus.furnitureshopping.feature.auth.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omerokumus.furnitureshopping.databinding.FragmentLoginBinding
import com.omerokumus.furnitureshopping.extensions.asEmailInput
import com.omerokumus.furnitureshopping.extensions.asForgotPasswordButton
import com.omerokumus.furnitureshopping.extensions.asLoginButton
import com.omerokumus.furnitureshopping.extensions.asPasswordInput
import com.omerokumus.furnitureshopping.extensions.asSignUpButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserById(1) // Example User fetched. Authenticated User will be used after login controls added.

        observeViewModel()
        binding.run {
            emailTextField.asEmailInput()
            passwordTextField.asPasswordInput()
            signUpButton.asSignUpButton()
            forgotPasswordButton.asForgotPasswordButton()
            loginButton.asLoginButton {
                return@asLoginButton true
            }
        }
    }

    private fun observeViewModel(){
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            viewModel.setUser(it)
        }
    }
}