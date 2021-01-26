package com.example.smartshoppinglist.db.type_converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateTypeConverter() {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toEpochSecond(date: LocalDate): Long {
        return date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(millis: Long): LocalDate {
        return Instant.ofEpochSecond(millis).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}