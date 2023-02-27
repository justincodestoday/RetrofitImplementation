package com.justin.productcatalog.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.justin.productcatalog.MyApplication
import com.justin.productcatalog.R
import com.justin.productcatalog.databinding.FragmentHomeBinding
import com.justin.productcatalog.ui.adapter.ProductAdapter
import com.justin.productcatalog.ui.presentation.product.UpdateProductFragment
import com.justin.productcatalog.ui.viewModel.HomeViewModel
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ProductAdapter

    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireActivity().application as MyApplication).productRepository)
    }

    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
//        binding?.lifecycleOwner = viewLifecycleOwner
//        binding?.viewModel = viewModel
        setupAdapter()

        binding?.btnAdd?.setOnClickListener {
            navController.navigate(R.id.addProductFragment)
        }

        fragmentResultListener()
    }

    override fun onBindData(view: View) {
        super.onBindData(view)

//        viewModel.getProducts()
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProducts(it)
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupAdapter()
//
//        viewModel.getProducts()
//        viewModel.products.observe(viewLifecycleOwner) {
//            adapter.setProducts(it)
//        }
//
////        lifecycleScope.launch {
////            viewModel.error.collect {
////                val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT)
////                snackbar.show()
////            }
////        }
//    }

    private fun setupAdapter() {
        adapter = ProductAdapter(
            mutableListOf()
        ) {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateProductFragment(it.id!!)
            navController.navigate(action)
        }
        val layoutManager = GridLayoutManager(this.activity, 2)
        binding?.rvProducts?.adapter = adapter
        binding?.rvProducts?.layoutManager = layoutManager
    }

    private fun fragmentResultListener() {
        setFragmentResultListener("from_add_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }

        setFragmentResultListener("from_update_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }
    }
}