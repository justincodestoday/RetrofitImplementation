package com.example.productcatalogue.ui.presentation

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
import com.example.productcatalogue.MyApplication
import com.example.productcatalogue.R
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.ui.adapter.ProductAdapter
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import com.example.productcatalogue.ui.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }
    private lateinit var adapter: ProductAdapter

    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()
        binding!!.btnAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddProduct()
            navController.navigate(action)
        }
        fragmentResultListener()
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProducts(it)
        }
    }

    private fun fragmentResultListener() {
        setFragmentResultListener("from_add_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }

        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }
    }

    private fun setupAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ProductAdapter(mutableListOf())
        adapter.listener = object : ProductAdapter.Listener {
            override fun onClick(product: Product) {
                val action = HomeFragmentDirections.actionHomeToDetails(product.id!!)
                NavHostFragment.findNavController(this@HomeFragment).navigate(action)
            }
        }
        binding!!.rvProducts.adapter = adapter
        binding!!.rvProducts.layoutManager = layoutManager
    }
}