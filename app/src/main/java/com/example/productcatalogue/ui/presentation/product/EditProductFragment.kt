package com.example.productcatalogue.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.ui.presentation.product.viewModel.EditProductViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditProductFragment : BaseProductFragment() {
    override val viewModel: EditProductViewModel by viewModels {
        EditProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }
    val product: MutableLiveData<Product> = MutableLiveData()


    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        val navArgs: EditProductFragmentArgs by navArgs()
        viewModel.getProductById(navArgs.id)
        viewModel.product.observe(viewLifecycleOwner) {
            binding?.apply {
                etBrand.setText(it.brand)
                etCategory.setText(it.category)
                etTitle.setText(it.title)
                etDes.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRate.setText(it.rating.toString())
                etStock.setText(it.stock.toString())
                btnAdd.text = "Update"

                btnAdd.setOnClickListener {
                    val product = getProduct()
                    product?.let {
                        viewModel.updateProduct(navArgs.id, it)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.finish.collect() {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_update_product", bundle)
                navController.popBackStack()
            }
        }
    }

//    override fun getLayoutResource(): Int = R.layout.fragment_add_products
}