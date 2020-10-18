package net.qamar.daggerexample.ui.main.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.qamar.daggerexample.ui.auth.AuthRepository
import net.qamar.daggerexample.util.SessionManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(    private val sessionManager: SessionManager
): ViewModel() {

    init {

        if(sessionManager!=null)
        Log.e("qmr","hey viewmodel from home")
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}