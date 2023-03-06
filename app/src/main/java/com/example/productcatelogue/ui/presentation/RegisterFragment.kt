package com.example.productcatelogue.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.productcatelogue.R
import com.example.productcatelogue.databinding.FragmentRegisterBinding
import com.example.productcatelogue.ui.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun getLayoutResource()=R.layout.fragment_register

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
            binding?.goLogin?.setOnClickListener {
                NavHostFragment.findNavController(this).navigate(R.id.loginFragment2)
            }
        binding?.run{
            register.setOnClickListener {
                val name= name.text.toString()
                val email=email.text.toString()
                val pass=password.text.toString()
                val confirmPass=confirmPassword.text.toString()
                viewModel.signUp(name,email,pass,confirmPass)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.finish.collect{
                val action = LoginFragmentDirections.toLoginFragment()
                navController.navigate(action)
            }
        }
    }
}