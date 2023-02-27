package com.justin.productcatalog.ui.presentation.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justin.productcatalog.R
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.databinding.FragmentAddProductBinding
import com.justin.productcatalog.ui.presentation.BaseFragment

abstract class BaseProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override fun getLayoutResource(): Int = R.layout.fragment_add_product

    fun getProduct(): Product? {
        return binding?.run {
            val brand = etBrand.text.toString()
            val category = etCategory.text.toString()
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val price = etPrice.text.toString()
            val discount = etDiscount.text.toString()
            val rating = etRating.text.toString()
            val stock = etStock.text.toString()
            Product(
                null,
                brand,
                category,
                title,
                description,
                price.toFloat(),
                discount.toFloat(),
                rating.toFloat(),
                stock.toInt(),
                "",
                null
            )
        }
    }
}