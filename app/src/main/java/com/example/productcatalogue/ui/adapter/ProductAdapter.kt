package com.example.productcatalogue.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.databinding.ItemProductLayoutBinding
import com.example.productcatalogue.utils.update

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductLayoutBinding.inflate(layoutInflater, parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val products = products[position]

        holder.binding.run {
            title.text = products.brand
            price.text = products.price.toString()

        }

    }
    override fun getItemCount(): Int {
        return products.size
    }

    fun setProduct(products: MutableList<Product>) {
        this.products = products
        update(emptyList(), products) { task1, task2 ->
            task1.id == task2.id
        }
    }

    class ProductHolder(val binding: ItemProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}

