package net.qamar.daggerexample.data.remote.auth

import io.reactivex.Flowable
import net.qamar.daggerexample.data.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi
{

    @GET("users/{id}")
    fun auth(
        @Path("id") id:Int
    ): Flowable<User>
}