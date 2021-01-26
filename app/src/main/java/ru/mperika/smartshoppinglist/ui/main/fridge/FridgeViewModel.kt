package ru.mperika.smartshoppinglist.ui.main.fridge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class FridgeViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        var text: String = if (it == 1) {
           "холодос"
        } else {
            "список покупок"
        }
        "Hello world from section: $text"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}