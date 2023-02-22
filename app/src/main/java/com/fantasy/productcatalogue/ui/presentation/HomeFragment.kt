package com.fantasy.productcatalogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fantasy.productcatalogue.R
import com.fantasy.productcatalogue.data.api.RetrofitClient
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.databinding.FragmentHomeBinding
import com.fantasy.productcatalogue.ui.adapter.ProductAdapter
import com.fantasy.productcatalogue.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var adapter: ProductAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        viewModel.getProducts()

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
