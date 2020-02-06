package com.gregory.retailstore.system.db.cart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {

    @Query("SELECT * FROM CartModel")
    fun getCart(): LiveData<List<CartModel>>

    @Query("SELECT * FROM CartModel WHERE productId=:id")
    fun findByProductId(id: Long): CartModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg items: CartModel)

    @Query("DELETE FROM CartModel")
    fun deleteAll()

    @Query("DELETE FROM CartModel WHERE productId=:id")
    fun delete(id: Long)
}

