package com.example.productcatalogue.ui.presentation.product

import com.example.productcatalogue.R
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.databinding.FragmentAddProductBinding
import com.example.productcatalogue.ui.presentation.BaseFragment

abstract class BaseProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override fun getLayoutResource(): Int = R.layout.fragment_add_product

    fun getProduct(): Product? {
        return binding!!.run {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val brand = etBrand.text.toString()
            val category = etCategory.text.toString()
            val price = etPrice.text.toString()
            val stock = etStock.text.toString()
            val discount = etDiscount.text.toString()
            val rating = etRating.text.toString()

            if (price.isEmpty() || stock.isEmpty() || discount.isEmpty() || rating.isEmpty()) {
                return null
            }

            Product(
                null,
                title,
                description,
                brand,
                category,
                price.toFloat(),
                stock.toInt(),
                discount.toFloat(),
                rating.toFloat(),
            )
        }
    }
}