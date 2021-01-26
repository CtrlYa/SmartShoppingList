package com.example.smartshoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.smartshoppinglist.db.type_converters.LocalDateTypeConverter
import java.time.LocalDate

@Entity(tableName = "products")
data class Product(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pr_id")
        var id: Int,
        @ColumnInfo(name = "pr_name")
        var productUserName: String,
        @ColumnInfo(name = "pr_brand")
        var productBrand: String,
        @ColumnInfo(name = "pr_category", defaultValue = "999")
        @TypeConverters(ProductCategory::class)
        var category: ProductCategory = ProductCategory.OTHER,
        @ColumnInfo(name = "pr_quantity", defaultValue = "0")
        var quantity: Int = 0,
        @ColumnInfo(name = "pr_exp_date", defaultValue = "0")
        @TypeConverters(LocalDateTypeConverter::class)
        var expirationDate: LocalDate,
        @ColumnInfo(name = "pr_image", defaultValue = "")
        var imagePath: String
        )
