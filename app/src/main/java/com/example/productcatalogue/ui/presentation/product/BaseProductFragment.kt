package com.example.productcatalogue.ui.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.R
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.databinding.FragmentAddProductsBinding
import com.example.productcatalogue.ui.presentation.BaseFragment
import com.example.productcatalogue.utils.Utils
import kotlinx.coroutines.launch

abstract class BaseProductFragment : BaseFragment<FragmentAddProductsBinding>() {
    override fun getLayoutResource(): Int = R.layout.fragment_add_products

    fun getProduct(): Product? {
        return binding?.run {
            val title = etTitle.text.toString()
            val desc = etDes.text.toString()
            val price = etPrice.text.toString()
            val discount = etDiscount.text.toString()
            val rating = etRate.text.toString()
            val stock = etStock.text.toString()
            val brand = etBrand.text.toString()
            val category = etCategory.text.toString()

            val validationStatus = Utils.validate(
                title,
                desc,
                price,
                discount,
                rating,
                stock,
                brand,
                category,
            )

            if (!validationStatus) {
                lifecycleScope.launch {
                    viewModel.error.emit("Please fill in every detail")
                }
                return null
            }


            Product(
                null, title, brand, category, desc,
                price.toFloat(), discount.toFloat(),
                rating.toFloat(), stock.toInt(), ""
            )
        }
    }
}