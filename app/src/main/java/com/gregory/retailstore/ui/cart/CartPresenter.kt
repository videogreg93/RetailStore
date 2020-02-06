package com.gregory.retailstore.ui.cart

import com.gregory.retailstore.system.db.cart.CartDto
import com.gregory.retailstore.system.managers.RetailStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartPresenter(private val view: View) {
    private val manager = RetailStoreManager.cartManager

    fun updateQuantity(cartDto: CartDto) {
        CoroutineScope(Dispatchers.Default).launch {
            val success = manager.updateCart(cartDto)
            withContext(Dispatchers.Main) {
                if (!success) {
                    view.onError()
                }
            }
        }
    }

    interface View {
        val presenter: CartPresenter

        fun onError()
    }
}