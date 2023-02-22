package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productcatalogue.R
import com.example.productcatalogue.ui.adapter.ProductAdapter
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.ui.viewModel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()
        setupAdapter()
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProduct(it)
        }
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(mutableListOf())
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = layoutManager
    }

}