package com.fantasy.productcatalogue.ui.presentation.product

import androidx.fragment.app.Fragment
import com.fantasy.productcatalogue.R
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.databinding.FragmentAddProductBinding
import com.fantasy.productcatalogue.ui.presentation.BaseFragment

abstract class BaseProductFragment: BaseFragment<FragmentAddProductBinding>() {
    override fun getLayoutResource(): Int = R.layout.fragment_add_product

    fun getProduct(): Product? {
        return binding?.run {
            val title = etTitle.text.toString()
            val desc = etDescription.text.toString()
            val price = etPrice.text.toString()
            val discount = etDiscount.text.toString()
            val rating = etRating.text.toString()
            val stock = etStock.text.toString()
            val brand = etBrand.text.toString()
            val category = etCategory.text.toString()
            Product(
               null, title, brand, category, desc,
               price.toFloat(), discount.toFloat(),
               rating.toFloat(), stock.toInt() , ""
            )
        }
    }
}