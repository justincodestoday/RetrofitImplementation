package com.justin.productcatalog.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.justin.productcatalog.ui.presentation.product.viewModel.UpdateProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateProductFragment : BaseProductFragment() {
    override val viewModel: UpdateProductViewModel by viewModels()
//    {
//        UpdateProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        val args: UpdateProductFragmentArgs by navArgs()
        viewModel.getProductById(args.id)
        viewModel.product.observe(viewLifecycleOwner) {
            binding?.apply {
                tvHeader.text = "Update Product"
                etBrand.setText(it.brand)
                etCategory.setText(it.category)
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
                etStock.setText(it.stock.toString())
                btnDelete.isVisible = true

                btnSave.setOnClickListener {
                    val product = getProduct()
                    product?.let {
                        viewModel.updateProduct(args.id, it)
                    }
                }

                btnDelete.setOnClickListener {
                    viewModel.deleteProduct(args.id)
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
                setFragmentResult("from_update_product", bundle)
                navController.popBackStack()
            }
        }
    }
}