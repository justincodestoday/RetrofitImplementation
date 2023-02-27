package com.example.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.ui.presentation.product.viewModel.AddProductViewModel
import kotlinx.coroutines.launch

class AddProductFragment : BaseProductFragment() {
    override val viewModel: AddProductViewModel by viewModels {
        AddProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding!!.run {
            btnAdd.setOnClickListener {
                val product = getProduct()
                product.let {
                    viewModel.addProduct(it)
                }
            }
        }
        binding!!.btnCancel.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        binding!!.run {
            tvHeader.text = "Add Product"
        }
        lifecycleScope.launch {
            viewModel.finish.collect {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_add_product", bundle)
                navController.popBackStack()
            }
        }
    }
}