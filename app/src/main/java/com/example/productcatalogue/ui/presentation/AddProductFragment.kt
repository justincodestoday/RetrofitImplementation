package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.productcatalogue.R
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentAddProductBinding
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.ui.viewModel.AddProductViewModel
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import com.example.productcatalogue.ui.viewModel.HomeViewModel

class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override val viewModel: AddProductViewModel by viewModels {
        AddProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_add_product

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
    }
}