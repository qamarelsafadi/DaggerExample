package net.qamar.daggerexample.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.data.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    var authUser = MediatorLiveData<Resource<User>>()

    fun getUserByID(sourceLiveData: LiveData<Resource<User>>) {
        if (authUser != null) {
            authUser.value = Resource.loading(User())

            authUser.addSource(sourceLiveData, Observer {
                authUser.value = it
                authUser.removeSource(sourceLiveData)
            })
        }
    }


    fun observerUser(): LiveData<Resource<User>> {
        return authUser
    }

}