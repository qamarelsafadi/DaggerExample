package net.qamar.daggerexample.data.remote.auth

import io.reactivex.Flowable
import net.qamar.daggerexample.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApi
{

    @GET("users/{id}")
    fun auth(
        @Path("id") id:Int
    ): Flowable<User>
}