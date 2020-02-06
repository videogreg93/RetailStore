package com.gregory.retailstore.mock

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gregory.retailstore.system.api.ProductApi
import com.gregory.retailstore.system.db.product.ProductDao
import com.gregory.retailstore.system.db.product.ProductModel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.mock


class MockObjects {

    fun getProductApi(): ProductApi {
        return mock(ProductApi::class.java)
    }

    fun getProductDao(): ProductDao {
        val productDao = mock(ProductDao::class.java)
        val products = arrayOf(
            ProductModel(1, "SampleProduct", ProductModel.CATEGORY.ELECTRONICS, 0f, null),
            ProductModel(3, "SampleProduct", ProductModel.CATEGORY.FURNITURE, 0f, null),
            ProductModel(2, "SampleProduct", ProductModel.CATEGORY.ELECTRONICS, 0f, null)
        )
        runBlocking {
            Mockito.`when`(productDao.findById(1)).thenReturn(products.first())
        }

        val liveData = MutableLiveData<List<ProductModel>>()
        Mockito.`when`(productDao.getAllProduct()).then {

            liveData.postValue(products.toList())
            Mockito.doReturn(liveData)
        }.thenReturn(liveData)

        return productDao
    }
}