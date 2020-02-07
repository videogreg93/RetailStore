package com.gregory.retailstore.system.managers

import android.content.Context
import com.gregory.retailstore.system.api.ProductApi

internal object RetailStoreManager {
    private lateinit var databaseManager: DatabaseManager

    lateinit var productManager: ProductManager
    lateinit var cartManager: CartManager

    fun initMainServices(context: Context) {
        databaseManager = DatabaseManager.init(context)
        productManager = ProductManager(ProductApi(), databaseManager.productDao)
        cartManager = CartManager(databaseManager.cartDao, productManager)
    }
}