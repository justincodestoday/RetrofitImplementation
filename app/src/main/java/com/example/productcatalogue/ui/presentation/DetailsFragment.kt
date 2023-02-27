package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.productcatalogue.R
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentDetailsBinding
import com.example.productcatalogue.ui.adapter.ProductAdapter
import com.example.productcatalogue.ui.viewModel.DetailsViewModel
import com.example.productcatalogue.ui.viewModel.HomeViewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_details

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        val navArgs: DetailsFragmentArgs by navArgs()
        val productId = navArgs.id
        viewModel.getProductById(productId)
        binding!!.btnBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_details", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding!!.btnEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsToEditProduct(productId)
            navController.navigate(action)
        }

        fragmentResultListener(productId)
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        viewModel.product.observe(viewLifecycleOwner) {
            binding.run {
                val price = "RM${it.price} per unit"
                val rating = "⭐${it.rating}"
                val discount = "${it.discountPercentage}%"
                val stock = "${it.stock} units available"
                this!!.tvTitle.text = it.title
                tvDescription.text = it.description
                tvBrand.text = it.brand
                tvPrice.text = price
                tvRating.text = rating
                tvDiscount.text = discount
                tvStock.text = stock

                if (it.thumbnail.isNotEmpty() && URLUtil.isValidUrl(it.thumbnail)) {
                    Glide.with(binding!!.root).load(it.thumbnail).into(ivImage)
                } else {
                    Glide.with(binding!!.root).load(R.drawable.ic_empty_folder).into(ivImage)
                }
            }
        }
    }

    private fun fragmentResultListener(productId: Int) {
        setFragmentResultListener("from_edit_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProductById(productId)
            }
        }
    }
}