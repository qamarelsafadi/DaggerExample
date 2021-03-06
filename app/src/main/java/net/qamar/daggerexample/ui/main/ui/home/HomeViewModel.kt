package net.qamar.daggerexample.ui.main.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.data.models.User
import net.qamar.daggerexample.ui.main.MainRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    init {
        if(repository!=null)
        Log.e("qmr","hey viewmodel from home")
    }

    fun getAuthenticatedUser(): LiveData<Resource<User>>{
        return repository.observerUser()


    }



}