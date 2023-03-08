package com.example.productcatalogue.ui.presentation.product

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.ui.presentation.product.viewModel.EditProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProductFragment : BaseProductFragment() {
    //    override val viewModel: EditProductViewModel by viewModels {
//        EditProductViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private var fileUri: Uri? = null
    override val viewModel: EditProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            fileUri = it
            it?.let { uri ->
                binding?.ivUploadImage?.setImageURI(uri)
            }
        }
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        val navArgs: EditProductFragmentArgs by navArgs()
        val productId = navArgs.id
        viewModel.getProductById(productId)
        binding.run {
            this!!.btnAdd.setOnClickListener {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                val brand = etBrand.text.toString()
                val category = etCategory.text.toString()
                val price = etPrice.text.toString()
                val stock = etStock.text.toString()
                val discount = etDiscount.text.toString()
                val rating = etRating.text.toString()
//                val thumbnail = viewModel.product.value?.thumbnail

                if (fileUri == null) {
                    val product = Product(
                        productId,
                        title,
                        description,
                        brand,
                        category,
                        price.toFloat(),
                        stock.toInt(),
                        discount.toFloat(),
                        rating.toFloat(),
                        viewModel.product.value?.thumbnail
                    )
                    viewModel.editProduct(
                        productId,
                        product,
                        fileUri
                    )
                } else {
                    val product = Product(
                        productId,
                        title,
                        description,
                        brand,
                        category,
                        price.toFloat(),
                        stock.toInt(),
                        discount.toFloat(),
                        rating.toFloat(),
                    )
                    viewModel.editProduct(
                        productId,
                        product.copy(thumbnail = product.thumbnail),
                        fileUri
                    )
                }
            }
            tvUploadImage.setOnClickListener {
                imagePickerLauncher.launch("image/*")
            }
        }
        binding!!.btnCancel.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        val navArgs: EditProductFragmentArgs by navArgs()
        val productId = navArgs.id
        viewModel.getProductById(productId)

        viewModel.product.observe(viewLifecycleOwner) {
            binding!!.run {
                tvHeader.text = "Edit Product"
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                etBrand.setText(it.brand)
                etCategory.setText(it.category)
                etPrice.setText(it.price.toString())
                etStock.setText(it.stock.toString())
                etDiscount.setText(it.discountPercentage.toString())
                etRating.setText(it.rating.toString())
            }
        }

        lifecycleScope.launch {
            viewModel.finish.collect {
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_edit_product", bundle)
                navController.popBackStack()
            }
        }
    }
}