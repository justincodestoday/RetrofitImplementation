package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
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

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: DetailsFragmentArgs by navArgs()

        viewModel.getProductById(navArgs.id)

        viewModel.product.observe(viewLifecycleOwner) {
            binding.run {
                val price = "RM${it.price} per unit"
                val rating = "⭐${it.rating}"
                val discount = "${it.discountPercentage}%"
                val stock = "${it.stock} units available"
                tvTitle.text = it.title
                tvDescription.text = it.description
                tvBrand.text = it.brand
                tvPrice.text = price
                tvRating.text = rating
                tvDiscount.text = discount
                tvStock.text = stock

                if (it.images.isNotEmpty() && URLUtil.isValidUrl(it.images[0])) {
                    Glide.with(binding.root).load(it.images[0]).into(ivImage)
                } else {
                    Glide.with(binding.root).load(R.drawable.ic_empty_folder).into(ivImage)
                }
            }
        }
        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this@DetailsFragment).popBackStack()
        }
    }
}