package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.productcatalogue.R
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentAddProductsBinding
import com.example.productcatalogue.ui.viewModel.AddProductViewModel

class AddProductsFragment : BaseFragment<FragmentAddProductsBinding>() {

    override val viewModel: AddProductViewModel by viewModels(){
        AddProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }
    override fun getLayoutResource(): Int = R.layout.fragment_add_products

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        binding?.run{
            btnAdd.setOnClickListener{
                val title = etTitle.text.toString()
                val desc = etDes.text.toString()
                val price = etPrice.text.toString()
                val discount = etDiscount.text.toString()
                val rating = etRate.text.toString()
                val stock = etStock.text.toString()
                val brand = etBrand.text.toString()
                val category = etCategory.text.toString()
                viewModel.addProduct(title,desc, price, discount, rating, stock, brand, category)

                val bundle = Bundle()
                bundle.putBoolean("refresh",true)
                setFragmentResult("from_add_product",bundle)
                navController.popBackStack()

            }
        }
    }


}