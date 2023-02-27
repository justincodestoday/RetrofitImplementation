package com.fantasy.productcatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fantasy.productcatalogue.R
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.databinding.ItemLayoutProductBinding
import com.fantasy.productcatalogue.ui.presentation.HomeFragmentDirections
import com.fantasy.productcatalogue.utils.Utils.update

class ProductAdapter(var products: MutableList<Product>, val onClick: (product: Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutProductBinding.inflate(layoutInflater, parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        holder.binding.run {
            tvBrand.text = product.brand
            tvTitle.text = product.title
            tvPrice.text = "$" + product.price.toString()
            cvProduct.setOnClickListener {
                onClick(product)
            }

            Glide.with(holder.binding.root)
                .load(product.thumbnail)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivImage)
        }
    }

    fun setProduct(products: MutableList<Product>) {
        val oldProducts = this.products
        this.products = products
//        notifyDataSetChanged()
        if (oldProducts.isEmpty()) {
            update(emptyList(), products) { task, task2 ->
                task.id == task2.id
            }
        } else {
            update(oldProducts, products) { task, task2 ->
                task.id == task2.id
            }
        }
    }

    override fun getItemCount() = products.size

    class ProductHolder(val binding: ItemLayoutProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}