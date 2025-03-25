package com.omerokumus.furnitureshopping.feature.auth.signup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omerokumus.furnitureshopping.databinding.FragmentSignUpBinding
import com.omerokumus.furnitureshopping.extensions.asConfirmPasswordInput
import com.omerokumus.furnitureshopping.extensions.asEmailInput
import com.omerokumus.furnitureshopping.extensions.asGoBack
import com.omerokumus.furnitureshopping.extensions.asNameInput
import com.omerokumus.furnitureshopping.extensions.asPasswordInput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSignUpBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        binding.run {
            emailTextField.asEmailInput()
            passwordTextField.asPasswordInput()
            confirmPasswordTextField.asConfirmPasswordInput(passwordTextField)
            nameTextField.asNameInput()
            signInButton.asGoBack()

            signUpButton.setOnClickListener {
                checkForEmptyFields()
                if (viewModel.isRegisterAllowed(getErrorMessagesList())) {
                    viewModel.createUser(
                        nameTextFieldEditText.text.toString(),
                        emailTextFieldEditText.text.toString(),
                        passwordTextFieldEditText.text.toString()
                    )
                }
            }
        }

    }

    private fun checkForEmptyFields(){
        if (viewModel.checkIfFieldEmpty(binding.nameTextFieldEditText.text.toString())){
            binding.nameTextField.error = "Name is required"
        }
        if (viewModel.checkIfFieldEmpty(binding.emailTextFieldEditText.text.toString())){
            binding.emailTextField.error = "Email is required"
        }
        if (viewModel.checkIfFieldEmpty(binding.passwordTextFieldEditText.text.toString())){
            binding.passwordTextField.error = "Password is required"
        }
        if (viewModel.checkIfFieldEmpty(binding.confirmPasswordTextFieldEditText.text.toString())){
            binding.confirmPasswordTextField.error = "Confirm Password is required"
        }
    }

    private fun getErrorMessagesList(): List<String> {
        val errorMessages = mutableListOf<String>()
        errorMessages.add(binding.nameTextField.error.toString())
        errorMessages.add(binding.emailTextField.error.toString())
        errorMessages.add(binding.passwordTextField.error.toString())
        errorMessages.add(binding.confirmPasswordTextField.error.toString())
        return errorMessages.toList()
    }

    private fun observeViewModel(){
        viewModel.doesUserCreated.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(requireContext(), "User created", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Email is already in use", Toast.LENGTH_SHORT).show()
            }
        }
    }
}