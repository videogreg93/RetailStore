package com.gregory.retailstore.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.product.ProductDto
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter(val listener: ((ProductDto) -> Unit)) :
    ListAdapter<ProductDto, ProductsAdapter.ProductViewHolder>(ProductDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ProductDto) {
            Glide.with(view)
                .load(item.imageUrl)
                .into(view.product_image)

            view.product_title.text = item.name
            view.product_category.text = view.context.getString(item.category.stringId)
            view.product_price.text = item.price.toString()

            view.setOnClickListener {
                listener.invoke(item)
            }
        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<ProductDto>() {

        override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem == newItem
        }
    }
}