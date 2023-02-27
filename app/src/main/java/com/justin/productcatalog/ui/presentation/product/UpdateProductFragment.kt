package com.justin.productcatalog.ui.presentation.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.justin.productcatalog.R
import com.justin.productcatalog.data.api.RetrofitClient
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.ui.presentation.product.viewModel.UpdateProductViewModel
import com.justin.productcatalog.ui.viewModel.BaseViewModel

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
                tvHeader.text = "Update Product"
                etBrand.setText(it.brand)
                etCategory.setText(it.category)
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
                etStock.setText(it.stock.toString())
            }
        }
    }
}