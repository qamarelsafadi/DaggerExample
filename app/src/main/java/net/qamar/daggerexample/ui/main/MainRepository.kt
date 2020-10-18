package net.qamar.daggerexample.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import net.qamar.daggerexample.data.models.Post
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.data.models.User
import net.qamar.daggerexample.data.remote.main.MainApi
import net.qamar.daggerexample.util.SessionManager
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi,
    private val application: Application
) {
    private val TAG = "PostsViewModel"

     var posts: MediatorLiveData<Resource<List<Post?>>>? =null

    fun observerUser(): LiveData<Resource<User>> {
        return sessionManager.observerUser()
    }

    fun observePosts(id:Int): LiveData<Resource<List<Post?>>> {
        if (posts == null) {
            posts = MediatorLiveData<Resource<List<Post?>>>()
            posts!!.value = Resource.loading(ArrayList<Post>())
            val sources: LiveData<Resource<List<Post?>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getUserPosts(id)
                    .onErrorReturn {
                        val post = Post()
                        post.id = -1
                        val arrayList = ArrayList<Post>()
                        arrayList.add(post)
                        arrayList
                    }.map { t: List<Post> ->
                        if (t.isNotEmpty()) {
                            if (t[0].id == -1)
                                Resource.error("Somthing went wong", null)
                        }
                        Resource.success(t)
                    }
                    .subscribeOn(Schedulers.io())

            )
            Log.d(TAG, "observePosts: user id: " + sessionManager.authUser.value!!.data!!.id)

            posts!!.addSource(sources, Observer {
                posts!!.value = it
                posts!!.removeSource(sources)
            })


        }
        return posts!!
    }


}