package net.qamar.daggerexample.data.remote.main

import io.reactivex.Flowable
import net.qamar.daggerexample.data.models.Post
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.data.models.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getUserPosts(
        @Query("userId") id:Int
    ): Flowable<List<Post>>
}