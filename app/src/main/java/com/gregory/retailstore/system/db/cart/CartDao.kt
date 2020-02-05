package com.gregory.retailstore.system.db.cart

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CartDao {

    @Query("SELECT * FROM CartModel")
    fun getCart(): LiveData<List<CartModel>>
}

