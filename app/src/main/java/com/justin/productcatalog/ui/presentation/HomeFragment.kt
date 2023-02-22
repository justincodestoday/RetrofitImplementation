package com.justin.productcatalog.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.justin.productcatalog.MyApplication
import com.justin.productcatalog.R
import com.justin.productcatalog.databinding.FragmentHomeBinding
import com.justin.productcatalog.databinding.ItemLayoutProductBinding
import com.justin.productcatalog.ui.adapter.ProductAdapter
import com.justin.productcatalog.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireActivity().application as MyApplication).productRepository)
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
            adapter.setProducts(it)
//            adapter = ProductAdapter(it)
//            val layoutManager = GridLayoutManager(this.activity, 2)
//            binding.rvProducts.adapter = adapter
//            binding.rvProducts.layoutManager = layoutManager
        }
    }

    private fun setupAdapter() {
        adapter = ProductAdapter(mutableListOf())
        val layoutManager = GridLayoutManager(this.activity, 2)
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = layoutManager
    }
}