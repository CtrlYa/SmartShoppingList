package ru.mperika.smartshoppinglist.data

import androidx.room.TypeConverter

enum class ProductCategory(var id: Int, var catName: String) {
    MEAL(1,"Еда без срока годности"),
    MEAL_WITH_EXP_DATE(2, "Еда со сроком годности"),
    OTHER(999, "Другое");



    companion object {
        @JvmStatic
        @TypeConverter
        fun fromProductCategory(category: ProductCategory): Int {
            return category.id
        }

        @JvmStatic
        @TypeConverter
        fun toProductCategory(id: Int) : ProductCategory {
            var value = values().find { category -> (category.id == id) }
            return value?: OTHER
        }
    }

    override fun toString(): String {
        return catName
    }


}

