package ru.mperika.smartshoppinglist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mperika.smartshoppinglist.data.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDAO() : ProductDAO
}