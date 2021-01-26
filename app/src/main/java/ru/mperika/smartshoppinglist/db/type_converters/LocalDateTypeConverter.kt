package ru.mperika.smartshoppinglist.db.type_converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateTypeConverter() {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        @TypeConverter
        fun fromExpirationDate(date: LocalDate): Long {
            return date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        @TypeConverter
        fun toExpirationDate(millis: Long): LocalDate {
            return Instant.ofEpochSecond(millis).atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }
}