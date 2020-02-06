package com.gregory.retailstore.system.db.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("Select * FROM PRODUCTMODEL")
    fun getAllProduct(): LiveData<List<ProductModel>>

    @Query("SELECT * FROM PRODUCTMODEL WHERE id = :id LIMIT 1")
    fun findById(id: Long): ProductModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(vararg productModel: ProductModel)
}