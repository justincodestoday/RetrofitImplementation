package com.justin.productcatalog.ui.presentation.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.justin.productcatalog.R
import com.justin.productcatalog.data.service.StorageService
import com.justin.productcatalog.databinding.FragmentProductDetailsBinding
import com.justin.productcatalog.ui.presentation.BaseFragment
import com.justin.productcatalog.ui.presentation.product.viewModel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>() {
    override val viewModel: ProductDetailsViewModel by viewModels()
    override fun getLayoutResource(): Int = R.layout.fragment_product_details
    val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getProduct(args.id)
            }
        }
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        viewModel.product.observe(viewLifecycleOwner) {
            binding?.run {
                tvTitle.text = it.title
                tvDescription.text = it.description
                tvBrand.text = it.brand
                tvPrice.text = "RM ${it.price}"

                it.thumbnail?.let { fileName ->
                    StorageService.getImageUri(fileName) {uri ->
                        Glide.with(view)
                            .load(uri)
                            .placeholder(R.drawable.insert_photo)
                            .into(ivProduct)
                    }
                }

                btnAddToCart.setOnClickListener {
                    viewModel.addToCart()
                }

                btnEdit.setOnClickListener {
                    val action =
                        ProductDetailsFragmentDirections.actionProductDetailsFragmentToUpdateProductFragment(
                            args.id
                        )
                    navController.navigate(action)
                }

                btnDelete.setOnClickListener {
                    viewModel.deleteProduct(args.id)
                }
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.delete.collect {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_delete_product", bundle)
                navController.popBackStack()
            }
        }
    }
}