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
import com.omerokumus.furnitureshopping.extensions.asPasswordInput
import com.omerokumus.furnitureshopping.extensions.asSignUpButton
import com.omerokumus.furnitureshopping.feature.main.MainActivity
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


        observeViewModel()

        binding.run {
            emailTextField.asEmailInput()
            passwordTextField.asPasswordInput()
            signUpButton.asSignUpButton()
            forgotPasswordButton.asForgotPasswordButton()

            loginButton.setOnClickListener {
                checkForEmptyFields()
                checkLogin()
            }
        }
    }

    private fun checkLogin() {
        if (viewModel.isLoginAllowed(
                binding.emailTextField.error.toString(),
                binding.passwordTextField.error.toString()
            )
        ) {
            viewModel.checkFirebaseAuthentication(
                binding.emailTextFieldEditText.text.toString(),
                binding.passwordTextFieldEditText.text.toString()
            )

        } else {
            Toast.makeText(
                requireContext(),
                "Email or password format incorrect, Please check error messages. ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkForEmptyFields(){
        if (viewModel.checkIfFieldEmpty(binding.emailTextFieldEditText.text.toString())){
            binding.emailTextField.error = "Email is required"
        }
        if (viewModel.checkIfFieldEmpty(binding.passwordTextFieldEditText.text.toString())){
            binding.passwordTextField.error = "Password is required"
        }
    }

    private fun observeViewModel() {
        viewModel.isLoginSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getUserByEmail(binding.emailTextFieldEditText.text.toString())
                Intent(requireContext(), MainActivity::class.java).also { intent ->
                    requireContext().startActivity(intent)
                    requireActivity().finish()
                }
            } else {
                Toast.makeText(requireContext(), "Email or password incorrect", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}