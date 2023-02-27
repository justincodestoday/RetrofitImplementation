package com.example.productcatelogue.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.productcatelogue.R
import com.example.productcatelogue.data.api.RetrofitClient
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.ui.presentation.BaseFragment
import com.example.productcatelogue.ui.product.viewModel.EditProductViewModel
import com.example.productcatelogue.ui.viewModel.BaseViewModel
import kotlinx.coroutines.launch

class EditProductFragment : BaseProductFragment() {
    override val viewModel: EditProductViewModel by viewModels{
        EditProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }
    override fun onBindData(view: View) {
        super.onBindData(view)

        val args: EditProductFragmentArgs by navArgs()
        viewModel.getProductById(args.id)
        viewModel.product.observe(viewLifecycleOwner) {
            binding?.apply {
                etBrand.setText(it.brand)
                etCat.setText(it.category)
                etTitle.setText(it.title)
                etDesc.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
                etStock.setText(it.stock.toString())
                btnAdd.text="Save"
                btnAdd.setOnClickListener {
                    val product = getProduct()
                    product?.let {
                        viewModel.updateProduct(args.id, it)
                    }

                }
            }
        }

        lifecycleScope.launch {
            viewModel.finish.collect {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_update_product", bundle)
                navController.popBackStack()
            }
        }
    }
}