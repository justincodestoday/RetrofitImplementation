package com.justin.productcatalog.ui.presentation.credential

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.justin.productcatalog.R
import com.justin.productcatalog.databinding.FragmentSignUpBinding
import com.justin.productcatalog.ui.presentation.BaseFragment
import com.justin.productcatalog.ui.presentation.credential.viewModel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val viewModel: SignUpViewModel by viewModels()
    override fun getLayoutResource(): Int {
        return R.layout.fragment_sign_up
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run {
            btnProceed.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val confirmPassword = etConfirmPassword.text.toString()
                viewModel.signUp(name, email, password, confirmPassword)
            }

            tvNavigate.setOnClickListener {
                navController.popBackStack()
            }
        }

//        viewModel.user.observe(viewLifecycleOwner) {
//            binding?.run {
//                etName.isVisible = true
//                etConfirmPassword.isVisible = true
////                tvNavigate.text = "Already have an account? Click here to sign in."
//                btnNavigate.text = "Click here to login"
//                btnProceed.text = "Register"
//
//                btnProceed.setOnClickListener {
//                    val name = etName.text.toString()
//                    val email = etEmail.text.toString()
//                    val password = etPassword.text.toString()
//                    val confirmPassword = etConfirmPassword.text.toString()
//                    viewModel.signUp(name, email, password, confirmPassword)
//                }
//
//
//                btnNavigate.setOnClickListener {
//                    navController.popBackStack()
//                }
////                tvNavigate.setOnClickListener {
////                    navController.popBackStack()
////                }
//            }
//        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.navigate.collect {
                navController.popBackStack()
            }
        }
    }
}