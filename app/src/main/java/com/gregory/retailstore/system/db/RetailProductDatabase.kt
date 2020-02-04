package com.gregory.retailstore.system.db

import androidx.room.Database
import com.gregory.retailstore.system.db.product.ProductModel

@Database(
    entities = [ProductModel::class],
    version = 1,
    exportSchema = false
)
internal abstract class RetailProductDatabase {
}