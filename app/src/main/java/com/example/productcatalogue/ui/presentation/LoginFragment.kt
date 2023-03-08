package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.productcatalogue.R
import com.example.productcatalogue.databinding.FragmentLoginBinding
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import com.example.productcatalogue.ui.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_login

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                viewModel.login(email, password)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.finish.collect {
                val action = LoginFragmentDirections.toHomeFragment()
                navController.navigate(action)
            }
        }

        binding!!.btnSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToSignUp()
            navController.navigate(action)
        }
    }
}