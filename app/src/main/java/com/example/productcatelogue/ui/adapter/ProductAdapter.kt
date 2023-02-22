package com.example.productcatelogue.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.databinding.ItemLayoutProductBinding
import com.example.productcatelogue.utils.update

class ProductAdapter(private var products: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ItemTaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutProductBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val product = products[position]
        holder.binding.run {
            tvTitle.text = product.title
            tvBrand.text = product.brand
            tvPrice.text = product.price.toString()
        }
    }

    fun setProducts(items: MutableList<Product>) {
        this.products = items.toMutableList()

        update(emptyList(), items) { task1, task2 ->
            task1.id == task2.id
        }
    }

    override fun getItemCount() = products.size


    class ItemTaskHolder(val binding: ItemLayoutProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}
