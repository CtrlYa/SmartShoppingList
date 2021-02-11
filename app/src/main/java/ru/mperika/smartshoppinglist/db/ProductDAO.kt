package ru.mperika.smartshoppinglist.db

import androidx.room.*
import ru.mperika.smartshoppinglist.data.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): MutableList<Product>

    @Update
    suspend fun update(vararg products: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product): Long

    @Delete
    suspend fun delete(product: Product)
}