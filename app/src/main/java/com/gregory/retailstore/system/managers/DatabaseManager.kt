package com.gregory.retailstore.system.managers

import android.content.Context
import com.gregory.retailstore.system.db.RetailProductDatabase
import com.gregory.retailstore.system.db.product.ProductDao

internal object DatabaseManager {
    private lateinit var db: RetailProductDatabase

    lateinit var productDao: ProductDao

    fun init(context: Context): DatabaseManager {
        db = RetailProductDatabase.setupDatabase(context)
        productDao = db.productDao()
        return this
    }
}