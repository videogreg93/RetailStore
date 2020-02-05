package com.gregory.retailstore.system.managers

import android.content.Context
import com.gregory.retailstore.system.api.ProductApi

internal object RetailStoreManager {
    lateinit var databaseManager: DatabaseManager

    lateinit var productManager: ProductManager

    fun initMainServices(context: Context) {
        databaseManager = DatabaseManager.init(context)
        productManager = ProductManager(ProductApi(), databaseManager.productDao)
    }
}