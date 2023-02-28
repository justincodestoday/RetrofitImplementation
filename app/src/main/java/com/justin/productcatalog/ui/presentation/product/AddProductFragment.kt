package com.justin.productcatalog.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.justin.productcatalog.MyApplication
import com.justin.productcatalog.R
import com.justin.productcatalog.data.api.RetrofitClient
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.databinding.FragmentAddProductBinding
import com.justin.productcatalog.ui.presentation.BaseFragment
import com.justin.productcatalog.ui.presentation.product.viewModel.AddProductViewModel
import com.justin.productcatalog.util.Utils
import com.justin.productcatalog.util.Utils.validate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddProductFragment : BaseProductFragment() {
    override val viewModel: AddProductViewModel by viewModels()
//    {
//        AddProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }

//    override fun getLayoutResource() = R.layout.fragment_add_product

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run {
            btnSave.setOnClickListener {
                val product = getProduct()
                product?.let {
                    viewModel.addProduct(it)
                }
//                val brand = etBrand.text.toString()
//                val category = etCategory.text.toString()
//                val title = etTitle.text.toString()
//                val description = etDescription.text.toString()
//                val price = etPrice.text.toString()
//                val discount = etDiscount.text.toString()
//                val rating = etRating.text.toString()
//                val stock = etStock.text.toString()

//                viewModel.addProduct(
//                    brand,
//                    category,
//                    title,
//                    description,
//                    price,
//                    discount,
//                    rating,
//                    stock,
//                )
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