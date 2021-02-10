package ru.mperika.smartshoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.mperika.smartshoppinglist.db.type_converters.LocalDateTypeConverter
import java.time.LocalDate
import java.util.*

@Entity(tableName = "products")
@TypeConverters(ProductCategory::class, LocalDateTypeConverter::class)
data class Product(
        @ColumnInfo(name = "pr_name")
        var productUserName: String,
        @ColumnInfo(name = "pr_brand")
        var productBrand: String,
        @ColumnInfo(name = "pr_category", defaultValue = "999")
        var category: ProductCategory = ProductCategory.OTHER,
        @ColumnInfo(name = "pr_quantity", defaultValue = "0")
        var quantity: Int = 0,
        @ColumnInfo(name = "pr_exp_date", defaultValue = "0")
        var expirationDate: Date,
        @ColumnInfo(name = "pr_image", defaultValue = "")
        var imagePath: String
        ) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pr_id")
        var id: Long? = null
}
