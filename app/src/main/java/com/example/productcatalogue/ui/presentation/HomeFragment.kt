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
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productcatalogue.R
import com.example.productcatalogue.ui.adapter.ProductAdapter
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.ui.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ProductAdapter
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupAdapter()
//    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProduct(it)
        }


        binding?.febAdd?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment()
            navController.navigate(action)
        }

        setFragmentResultListener("from_add_product"){_,result ->
            val refresh = result.getBoolean("refresh")
            if(refresh){
                viewModel.getProducts()
            }
        }


    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(mutableListOf())
        binding?.rvProducts?.adapter = adapter
        binding?.rvProducts?.layoutManager = layoutManager
    }

}