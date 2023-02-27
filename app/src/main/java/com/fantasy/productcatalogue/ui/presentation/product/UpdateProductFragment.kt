package com.fantasy.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.fantasy.productcatalogue.data.api.RetrofitClient
import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.ui.presentation.product.viewModel.UpdateProductViewModel
import kotlinx.coroutines.launch

class UpdateProductFragment : BaseProductFragment() {
    override val viewModel: UpdateProductViewModel by viewModels {
        UpdateProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        val args: UpdateProductFragmentArgs by navArgs()
        viewModel.getProductById(args.id)
        viewModel.product.observe(viewLifecycleOwner) {
            binding?.apply {
                textView.text = "Update Product"
                etBrand.setText(it.brand)
                etCategory.setText(it.category)
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
                etStock.setText(it.stock.toString())
                btnSave.text = "Update"

                btnSave.setOnClickListener {
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