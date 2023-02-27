package com.example.productcatelogue.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.productcatelogue.R
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.databinding.FragmentAddBinding
import com.example.productcatelogue.ui.presentation.BaseFragment

abstract class BaseProductFragment : BaseFragment<FragmentAddBinding>() {
    override fun getLayoutResource(): Int = R.layout.fragment_add

    fun getProduct(): Product?{
        return binding?.run{
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()
            val price = etPrice.text.toString()
            val brand = etBrand.text.toString()
            val discount = etDiscount.text.toString()
            val category = etCat.text.toString()
            val stock = etStock.text.toString()
            val rating = etRating.text.toString()
            Product(
                brand,
                category,
                desc,
                discount.toDouble(),
                null,
                null,
                price.toInt(),
                rating.toDouble(),
                stock.toInt(),
                "",
                title
            )
        }
    }

}