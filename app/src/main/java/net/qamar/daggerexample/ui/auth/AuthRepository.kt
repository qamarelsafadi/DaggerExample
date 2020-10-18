package net.qamar.daggerexample.ui.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import net.qamar.daggerexample.data.remote.auth.AuthApi
import net.qamar.daggerexample.models.Resource
import net.qamar.daggerexample.models.User
import net.qamar.daggerexample.util.SessionManager
import javax.inject.Inject


class AuthRepository @Inject constructor(var application: Application, private val api: AuthApi,
                                         private val sessionManager: SessionManager) {

  //  var authUser = MediatorLiveData<Resource<User>>()

    init {


        if (api == null)
            Log.d("qmr", "api is null")
        else
            Log.d("qmr", "api is not  null")

        Log.e("qmr", "Hello From Repository")
        // getUserByID()
    }


    fun getUserByID(userID: Int) {

        sessionManager.getUserByID(queryId(userID))

    }

    fun queryId(userID: Int):LiveData<Resource<User>>{
        return  LiveDataReactiveStreams.fromPublisher(
            api.auth(userID)
                .onErrorReturn {
                    val user = User()
                    user.id =-1
                    user
                }
                .map(object : Function<User, Resource<User>>{
                    override fun apply(user: User): Resource<User> {

                        if(user.id == -1){
                            return Resource.error("this user is not authenticated", User())
                        }
                        return Resource.authenticated(user)
                    }
                })
                .subscribeOn(Schedulers.io())


        )
    }
    fun observerUser(): LiveData<Resource<User>> {
        return sessionManager.observerUser()
    }



}