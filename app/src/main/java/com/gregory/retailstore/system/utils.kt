package com.gregory.retailstore.system

import java.io.IOException
import java.nio.charset.Charset
import java.text.DecimalFormat

object Utils {
    fun loadJson(fileName: String): String? {
        return try {
            this.javaClass.classLoader?.getResourceAsStream(fileName)?.let { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charset.defaultCharset())
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}

fun Float.toPrice() = DecimalFormat("0.00 '$'").format(this).replace(".", ",")