package com.example.productcatelogue.ui.presentations

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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    //    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
//        viewModel.getProduct()
        setUpAdapter()
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.products.observe(viewLifecycleOwner) {
            Log.d("debug", it.toString())
            adapter.setProducts(it)
        }
        binding?.add?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentHomeBinding.inflate(layoutInflater)
//        return binding.root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setUpAdapter()
//
//        viewModel.getProduct()
//
//        viewModel.products.observe(viewLifecycleOwner) {
//            adapter.setProducts(it)
//            val layoutManager = LinearLayoutManager(requireContext())
//            adapter = ProductAdapter(
//                it as MutableList<Product>
//            )
//            binding.rvTasks.adapter = adapter
//            binding.rvTasks.layoutManager = layoutManager


//    }

    fun setUpAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(
            mutableListOf()
        )
        binding?.rvTasks?.adapter = adapter
        binding?.rvTasks?.layoutManager = layoutManager
    }
}