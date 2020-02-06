package com.gregory.retailstore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gregory.retailstore.system.db.cart.CartDto
import com.gregory.retailstore.system.managers.RetailStoreManager

class CartViewModel : ViewModel() {
    private val cartManager = RetailStoreManager.cartManager

    var cart: LiveData<List<CartDto>> = cartManager.getCart()
}