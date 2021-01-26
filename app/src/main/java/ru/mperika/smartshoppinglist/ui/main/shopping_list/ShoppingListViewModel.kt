package ru.mperika.smartshoppinglist.ui.main.shopping_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingListViewModel: ViewModel() {

    private var values = mutableListOf("ldf", "four", "six", "eleven")

    private var data: MutableLiveData<MutableList<String>> = MutableLiveData<MutableList<String>>(values)

    fun getData() : MutableLiveData<MutableList<String>> {
        return data
    }

    fun addStr(str: String) {
        values.add(str)
        data.value = values
    }

}