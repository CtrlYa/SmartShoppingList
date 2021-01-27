package ru.mperika.smartshoppinglist.db.type_converters

import androidx.room.TypeConverter
import java.util.*

class LocalDateTypeConverter() {

    companion object {

        @JvmStatic
        @TypeConverter
        fun fromExpirationDate(date: Date): Long {
            return date.time
        }

        @JvmStatic
        @TypeConverter
        fun toExpirationDate(millis: Long): Date {
            return Date(millis)
        }
    }
}