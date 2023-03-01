package com.example.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.productcatalogue.R
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentAddProductsBinding
import com.example.productcatalogue.ui.presentation.BaseFragment
import com.example.productcatalogue.ui.presentation.product.viewModel.AddProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class AddProductsFragment : BaseProductFragment() {

    override val viewModel: AddProductViewModel by viewModels()


    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding?.run{
            btnAdd.setOnClickListener {
                val product = getProduct()
                product?.let {
                    viewModel.addProduct(it)
                }
            }
            btnDelete.visibility = View.GONE
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.finish.collect(){
                val bundle = Bundle()
                bundle.putBoolean("refresh",true)
                setFragmentResult("from_add_product",bundle)
                navController.popBackStack()

            }
        }
    }


}