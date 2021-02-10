package ru.mperika.smartshoppinglist.ui.edit

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.db.AppDatabase
import ru.mperika.smartshoppinglist.ui.main.fridge.EditStatus

class EditViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val data = MutableLiveData<EditStatus>(EditStatus.NONE)

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            val db = AppDatabase.getInstance(context)
            val id = db.productDAO().insertProduct(product)
            if (id > 0) {
                Log.d("INSERT COROUTINE", "Внесли в базу, id: $id")
                data.value = EditStatus.SUCCESS
            } else {
                data.value = EditStatus.FAILURE
            }
        }
    }
}