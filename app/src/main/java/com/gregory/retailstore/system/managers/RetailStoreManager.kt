package com.gregory.retailstore.system.managers

import android.content.Context

internal object RetailStoreManager {
    lateinit var databaseManager: DatabaseManager

    fun initMainServices(context: Context) {
        databaseManager = DatabaseManager.init(context)
    }
}