package com.gregory.retailstore.system

import java.io.IOException
import java.nio.charset.Charset

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