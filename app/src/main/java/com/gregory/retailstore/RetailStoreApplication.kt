package com.gregory.retailstore

import android.app.Application
import com.gregory.retailstore.system.managers.RetailStoreManager

class RetailStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RetailStoreManager.initMainServices(this)
    }
}