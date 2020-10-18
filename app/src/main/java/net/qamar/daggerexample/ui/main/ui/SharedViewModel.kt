package net.qamar.daggerexample.ui.main.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val id = MutableLiveData<Int>()

    fun sendId(text: Int) {
        id.value = text
    }
}