package com.gregory.retailstore.ui.products

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gregory.retailstore.system.managers.RetailStoreManager

class ProductViewModel : ViewModel() {
    private val productManager = RetailStoreManager.productManager

    val products by lazy { productManager.getSortedLiveProducts() }

    init {
        productManager.fetchAllProducts()
    }
}