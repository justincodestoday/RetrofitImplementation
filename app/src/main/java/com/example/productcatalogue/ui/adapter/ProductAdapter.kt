package com.example.productcatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productcatalogue.R
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.databinding.ItemProductLayoutBinding
import com.example.productcatalogue.utils.update

class ProductAdapter(private var products: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ItemProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemProductHolder, position: Int) {
        val item = products[position]
        holder.binding.run {
            val caption = "${item.category} at RM${item.price}"
            tvTitle.text = item.title
            tvBrand.text = item.brand
            tvCaption.text = caption
            tvRating.text = item.rating.toString()
            if (item.images.isNotEmpty() && URLUtil.isValidUrl(item.images[0])) {
                Glide.with(holder.binding.root)
                    .load(item.images[0]).into(ivImage)
            } else {
                Glide.with(holder.binding.root)
                    .load(R.drawable.ic_empty_folder).into(ivImage)
            }
        }
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(products: MutableList<Product>) {
        this.products = products
        update(mutableListOf(), this.products) { product1, product2 ->
            product1.id == product2.id
        }
    }

    class ItemProductHolder(val binding: ItemProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}