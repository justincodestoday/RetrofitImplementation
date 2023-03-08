package com.justin.productcatalog.ui.presentation.credential

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.justin.productcatalog.R
import com.justin.productcatalog.databinding.FragmentSignInBinding
import com.justin.productcatalog.ui.presentation.BaseFragment
import com.justin.productcatalog.ui.presentation.credential.viewModel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override val viewModel: SignInViewModel by viewModels()
    override fun getLayoutResource(): Int {
        return R.layout.fragment_sign_in
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run {
            btnProceed.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                Log.d("debug", "sign in")
                viewModel.signIn(email, password)
            }

            tvNavigate.setOnClickListener {
                val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
                NavHostFragment.findNavController(this@SignInFragment).navigate(action)
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.navigate.collect {
                val action = SignInFragmentDirections.toHomeFragment()
                navController.navigate(action)
            }
        }
    }
}