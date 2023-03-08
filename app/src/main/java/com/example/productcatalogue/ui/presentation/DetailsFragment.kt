package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.webkit.URLUtil
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.productcatalogue.R
import com.example.productcatalogue.data.service.StorageService
import com.example.productcatalogue.databinding.FragmentDetailsBinding
import com.example.productcatalogue.ui.viewModel.DetailsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
//    override val viewModel: DetailsViewModel by viewModels {
//        DetailsViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }

    override val viewModel: DetailsViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_details
    val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getProductById(args.id)
            }
        }
    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)

//        val navArgs: DetailsFragmentArgs by navArgs()
//        val productId = navArgs.id
//        viewModel.getProductById(productId)
        binding!!.btnBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_details", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        binding!!.btnAddToCart.setOnClickListener {
            viewModel.addToCart()
        }

        binding!!.btnEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsToEditProduct(args.id)
            navController.navigate(action)
        }

        binding!!.btnDelete.setOnClickListener {
            val title = binding!!.tvTitle.text
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            MaterialAlertDialogBuilder(requireContext(), R.style.CoffeeApp_AlertDialog)
                .setTitle("Delete $title?")
                .setCancelable(true)
                .setPositiveButton("Delete") { _, it ->
                    viewModel.deleteProduct(args.id)
                    setFragmentResult("from_delete_product", bundle)
                    NavHostFragment.findNavController(this).popBackStack()
                    val snackbar = Snackbar.make(
                        view,
                        "Product successfully deleted",
                        Snackbar.LENGTH_SHORT
                    )
                    val view2 = snackbar.view
                    val params = view2.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.TOP
                    view2.layoutParams = params
                    snackbar
                        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.almond))
                        .setActionTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.chestnut
                            )
                        )
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.smoky))
                        .show()
                }.setNegativeButton("Cancel") { _, it ->
                }.show()
        }

        fragmentResultListener(args.id)
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

                it.thumbnail?.let { it1 ->
                    StorageService.getImageUri(it1) {
                        Glide.with(requireContext())
                            .load(it)
                            .placeholder(R.drawable.ic_empty_folder)
                            .into(ivImage)
                    }
                }
            }
        }
    }

    private fun fragmentResultListener(productId: String) {
        setFragmentResultListener("from_edit_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProductById(productId)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val bundle = Bundle()
        bundle.putBoolean("refresh", true)
        setFragmentResult("from_details", bundle)
    }
}