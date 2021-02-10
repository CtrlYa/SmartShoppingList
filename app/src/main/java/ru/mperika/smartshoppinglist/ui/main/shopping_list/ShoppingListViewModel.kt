package ru.mperika.smartshoppinglist.ui.main.shopping_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mperika.smartshoppinglist.data.Product

class ShoppingListViewModel: ViewModel() {

    private var values = mutableListOf<Product>()

    private var data = MutableLiveData(values)

    fun getData() : MutableLiveData<MutableList<Product>> {
        return data
    }

    fun addProduct(product: Product) {
        values.add(product)
        data.value = values
    }

}