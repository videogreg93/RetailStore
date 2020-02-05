package com.gregory.retailstore.system.managers

import android.content.Context
import com.gregory.retailstore.system.db.RetailProductDatabase
import com.gregory.retailstore.system.db.cart.CartDao
import com.gregory.retailstore.system.db.product.ProductDao

internal object DatabaseManager {
    private lateinit var db: RetailProductDatabase

    lateinit var productDao: ProductDao
    lateinit var cartDao: CartDao

    fun init(context: Context): DatabaseManager {
        db = RetailProductDatabase.setupDatabase(context)
        productDao = db.productDao()
        cartDao = db.cartDao()

        return this
    }
}