package com.justin.productcatalog.ui.presentation.credential

import com.justin.productcatalog.R
import com.justin.productcatalog.databinding.FragmentSignInBinding
import com.justin.productcatalog.ui.presentation.BaseFragment

abstract class BaseCredentialFragment : BaseFragment<FragmentSignInBinding>() {

    override fun getLayoutResource(): Int = R.layout.fragment_sign_in
}