package com.example.philosophyblog.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    fun convertMongoDate(date: String): String? {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        try {
            val finalStr: String = outputFormat.format(inputFormat.parse(date))
            println(finalStr)
            return finalStr
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}