package com.gregory.retailstore.system.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gregory.retailstore.system.db.converters.RoomConverters
import com.gregory.retailstore.system.db.product.ProductDao
import com.gregory.retailstore.system.db.product.ProductModel

@Database(
    entities = arrayOf(ProductModel::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RoomConverters::class
)
abstract class RetailProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        fun setupDatabase(context: Context): RetailProductDatabase {
            return  Room.databaseBuilder(
                context,
                RetailProductDatabase::class.java, "DastabseName"
            ).build()
        }
    }
}