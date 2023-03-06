package com.example.productcatelogue.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.productcatelogue.ui.product.viewModel.EditProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class EditProductFragment : BaseProductFragment() {
    override val viewModel: EditProductViewModel by viewModels()

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