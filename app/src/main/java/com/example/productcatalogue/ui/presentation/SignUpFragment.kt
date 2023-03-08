package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.productcatalogue.R
import com.example.productcatalogue.databinding.FragmentSignUpBinding
import com.example.productcatalogue.ui.viewModel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val viewModel: SignUpViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_sign_up

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run {
            btnSignUp.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val confirmPassword = etPassword2.text.toString()

                viewModel.signUp(name, email, password, confirmPassword)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.finish.collect {
                val action = SignUpFragmentDirections.actionSignUpToLogin()
                navController.navigate(action)
            }
        }
    }
}