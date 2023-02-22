package com.example.productcatelogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productcatelogue.R
import com.example.productcatelogue.data.api.RetrofitClient
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.databinding.FragmentHomeBinding
import com.example.productcatelogue.ui.adapter.ProductAdapter
import com.example.productcatelogue.ui.viewModel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = layoutManager
    }
}