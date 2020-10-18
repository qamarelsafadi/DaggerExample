package net.qamar.daggerexample.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.qamar.daggerexample.models.Resource
import net.qamar.daggerexample.models.User
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun authWithID(userID: Int) {
        repository.getUserByID(userID)
    }

    fun observerUser(): LiveData<Resource<User>>  {
        return repository.observerUser()
    }

}