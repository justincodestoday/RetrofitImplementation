package com.fantasy.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fantasy.productcatalogue.R
import com.fantasy.productcatalogue.data.api.RetrofitClient
import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.databinding.FragmentAddProductBinding
import com.fantasy.productcatalogue.ui.presentation.BaseFragment
import com.fantasy.productcatalogue.ui.presentation.product.viewModel.AddProductViewModel
import kotlinx.coroutines.launch

class AddProductFragment : BaseProductFragment() {
    override val viewModel: AddProductViewModel by viewModels() {
        AddProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

//    override fun getLayoutResource(): Int = R.layout.fragment_add_product

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run {
            btnSave.setOnClickListener {
                val product = getProduct()
                product?.let {
                    viewModel.addProduct(it)
                }
            }
        }
    }
    override fun onBindData(view: View) {
        super.onBindData(view)

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