package com.gregory.retailstore.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.cart.CartDto
import com.gregory.retailstore.system.toPrice
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(val onQuantityChange: ((CartDto) -> Unit), val listener: ((CartDto) -> Unit)) :
    ListAdapter<CartDto, CartAdapter.ProductViewHolder>(CartDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CartDto) {
            val product = item.productDto
            view.cart_item_title.text = product.name
            view.cart_item_quantity.text = item.quantity.toString()
            view.cart_item_price.text = (item.quantity * product.price).toPrice()

            // Listeners
            view.cart_item_remove_button.setOnClickListener {
                changeQuantity(item, -1)
            }
            view.cart_item_add_button.setOnClickListener {
                changeQuantity(item, 1)
            }
            view.cart_item_title.setOnClickListener {
                listener(item)
            }
        }

        private fun changeQuantity(item: CartDto, quantityChange: Int) {
            item.quantity += quantityChange
            view.cart_item_quantity.text = item.quantity.toString()
            view.cart_item_price.text = (item.quantity * item.productDto.price).toPrice()
            onQuantityChange(item)
        }
    }

    class CartDiffUtil : DiffUtil.ItemCallback<CartDto>() {

        override fun areItemsTheSame(oldItem: CartDto, newItem: CartDto): Boolean {
            return oldItem.productDto.id == newItem.productDto.id
        }

        override fun areContentsTheSame(oldItem: CartDto, newItem: CartDto): Boolean {
            return oldItem.quantity == newItem.quantity
        }
    }
}