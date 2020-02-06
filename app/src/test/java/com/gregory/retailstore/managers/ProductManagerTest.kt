package com.gregory.retailstore.managers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gregory.retailstore.mock.MockObjects
import com.gregory.retailstore.system.managers.ProductManager
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProductManagerTest() {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    val mockObjects = MockObjects()
    val manager = ProductManager(
        mockObjects.getProductApi(),
        mockObjects.getProductDao()
    )

    @Test
    fun getProductById() {
        runBlocking {
            val product = manager.getProductById(1)
            assert(product?.id == 1L)
        }
    }
}