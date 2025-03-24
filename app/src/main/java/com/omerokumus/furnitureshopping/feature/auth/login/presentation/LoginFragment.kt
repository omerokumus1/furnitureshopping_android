package com.omerokumus.furnitureshopping.feature.auth.login.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omerokumus.furnitureshopping.databinding.FragmentLoginBinding
import com.omerokumus.furnitureshopping.extensions.asEmailInput
import com.omerokumus.furnitureshopping.extensions.asForgotPasswordButton
import com.omerokumus.furnitureshopping.extensions.asLoginButton
import com.omerokumus.furnitureshopping.extensions.asPasswordInput
import com.omerokumus.furnitureshopping.extensions.asSignUpButton
import com.omerokumus.furnitureshopping.extensions.findActivity
import com.omerokumus.furnitureshopping.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

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
        viewModel.checkLoggedInUser()

        binding.run {
            emailTextField.asEmailInput()
            passwordTextField.asPasswordInput()
            signUpButton.asSignUpButton()
            forgotPasswordButton.asForgotPasswordButton()

            loginButton.setOnClickListener {
                if (viewModel.isLoginAllowed(
                        emailTextField.error.toString(),
                        passwordTextField.error.toString()
                    )
                ) {
                    viewModel.checkFirebaseAuthentication(
                        emailTextFieldEditText.text.toString(),
                        passwordTextFieldEditText.text.toString()
                    )

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Email or password format incorrect, Please check error messages. ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.isLoginSuccessful.observe(viewLifecycleOwner) {
            Intent(requireContext(), MainActivity::class.java).also {
                requireContext().startActivity(it)
                requireActivity().finish()
            }
        }
    }
}