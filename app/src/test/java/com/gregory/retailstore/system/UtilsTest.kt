package com.gregory.retailstore.system

import org.junit.Assert
import org.junit.Test

class UtilsTest {
    @Test
    fun numberToPrice() {
        var price = 39.99
        Assert.assertEquals("39,99 $", price.toPrice())
        price = 1.0
        Assert.assertEquals("1,00 $", price.toPrice())
    }
}