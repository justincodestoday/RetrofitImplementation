package com.example.productcatelogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.databinding.ProductLayoutBinding
import com.example.productcatelogue.utils.update

class ProductAdapter(private var items: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater =LayoutInflater.from(parent.context)
        val binding=ProductLayoutBinding.inflate(layoutInflater,parent,false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = items[position]
        holder.binding.run{
            tvTitle.text=item.title
            tvPrice.text=item.price.toString()
            tvBrand.text=item.brand

        }
    }
    fun setProduct(items: List<Product>) {
        this.items = items.toMutableList()
//        notifyDataSetChanged()
//        val oldItems = this.items
//        this.items.clear()
//        this.items.addAll(items)
        update(emptyList(), items) { task1, task2 ->
            task1.id == task2.id
        }

    }
    override fun getItemCount()=items.size

    class ProductHolder(val binding: ProductLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}