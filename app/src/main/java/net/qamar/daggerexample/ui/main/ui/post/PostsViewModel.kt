package net.qamar.daggerexample.ui.main.ui.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.qamar.daggerexample.data.models.Post
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.ui.main.MainRepository
import javax.inject.Inject

class PostsViewModel  @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    init {
        if(repository!=null)
            Log.e("qmr","hey viewmodel from post")
    }


fun getObserver(id: Int) : LiveData<Resource<List<Post?>>> {
    return repository.observePosts(id)
}






}