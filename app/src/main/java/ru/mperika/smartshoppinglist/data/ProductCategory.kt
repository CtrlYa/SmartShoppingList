package ru.mperika.smartshoppinglist.data

import androidx.room.TypeConverter

enum class ProductCategory(var id: Int, name: String) {
    MEAL(1,"Еда без срока годности"),
    MEAL_WITH_EXP_DATE(2, "Еда со сроком годности"),
    OTHER(999, "Другое");


    @TypeConverter
    fun getID(): Int {
        return id
    }

    companion object {
        @TypeConverter
        fun getCategory(id: Int) : ProductCategory {
            var value = values().find { category -> (category.id == id) }
            return value?: OTHER
        }
    }

}

