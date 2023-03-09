package com.justin.productcatalog.ui.presentation.product

import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.justin.productcatalog.R
import com.justin.productcatalog.data.service.StorageService
import com.justin.productcatalog.ui.presentation.product.viewModel.UpdateProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateProductFragment : BaseProductFragment() {
    override val viewModel: UpdateProductViewModel by viewModels()
//    {
//        UpdateProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

        val args: UpdateProductFragmentArgs by navArgs()
        viewModel.getProductById(args.id)

        imageGallery = registerForActivityResult(ActivityResultContracts.GetContent()) {
            fileUri = it
            it?.let { uri ->
                binding?.run {
                    ivImage.setImageURI(uri)
                    tvPath.text = requireContext().contentResolver.getFileName(uri)
                }
            }
        }


        binding?.tvUploadImage?.setOnClickListener {
            imageGallery.launch("image/*")
        }

        viewModel.product.observe(viewLifecycleOwner) {
            binding?.apply {
                tvHeader.text = "Update Product"
                etBrand.setText(it.brand)
                etCategory.setText(it.category)
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                etPrice.setText(it.price.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
                etStock.setText(it.stock.toString())

                it.thumbnail?.let { thumbnail ->
                    StorageService.getImageUri(thumbnail) { uri ->
                        Glide.with(view)
                            .load(uri)
                            .placeholder(R.drawable.insert_photo)
                            .into(ivImage)
                        tvPath.text = thumbnail
                        tvPath.movementMethod = ScrollingMovementMethod()
                    }
                }

                btnSave.setOnClickListener {
                    val product = getProduct()
                    product?.let {
                        viewModel.deleteImage(it)
                        viewModel.updateProductWithNewImage(args.id, it, fileUri)
                    }
                }
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.finish.collect {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_update_product", bundle)
                navController.popBackStack()
            }
        }
    }
}