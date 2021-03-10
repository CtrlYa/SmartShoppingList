package ru.mperika.smartshoppinglist.ui.main.all_items

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.data.ProductCategory
import ru.mperika.smartshoppinglist.db.AppDatabase
import java.util.*

class AllItemsViewModel(_application: Application) : AndroidViewModel(_application) {
    private val app = _application
    private var values = mutableListOf<Product>(Product("Молоко", "ДвД", ProductCategory.OTHER, 0, Date(0), ""),
        Product("Колбаса", "ДЫМ", ProductCategory.OTHER, 0, Date(0), ""),
        Product("Торт", "Саныч", ProductCategory.OTHER, 0, Date(0), ""))

    private var data = MutableLiveData(values)

    init {
//        updateAllData()
    }

    fun getData() : MutableLiveData<MutableList<Product>> {
        return data
    }

    fun addProduct(product: Product) {
        values.add(product)
        data.value = values
    }

    fun updateAllData() {
        viewModelScope.launch {
            val db = AppDatabase.getInstance(app.applicationContext)
            val newValues = db.productDAO().getAllProducts()
            if (newValues.isNotEmpty()) {
                values.clear()
                values.addAll(newValues)
            }
            data.value = values
        }
    }
}