package com.example.productcatelogue.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.productcatelogue.R
import com.example.productcatelogue.databinding.FragmentLoginBinding
import com.example.productcatelogue.databinding.FragmentRegisterBinding
import com.example.productcatelogue.ui.viewModel.LoginViewModel
import com.example.productcatelogue.ui.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun getLayoutResource()=R.layout.fragment_login

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.goLogin?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.registerFragment2)
        }
        binding?.run{
            register.setOnClickListener {
                val email = email.text.toString()
                val pass= password.text.toString()
                viewModel.login(email,pass)
            }

        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.loginFinish.collect{
                val action = LoginFragmentDirections.toHomeFragment()
                navController.navigate(action)
            }
        }
    }


}