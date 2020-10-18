package net.qamar.daggerexample.ui.main.ui.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.fragment_posts.progress_bar
import kotlinx.android.synthetic.main.fragment_posts.view.*
import net.qamar.daggerexample.R
import net.qamar.daggerexample.data.models.Post
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.ui.main.ui.SharedViewModel
import net.qamar.daggerexample.util.VerticalSpaceItemDecoration
import net.qamar.daggerexample.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    private lateinit var postsViewModel: PostsViewModel
lateinit var   model :SharedViewModel
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var postRecyclerAdapter: PostRecyclerAdapter
    lateinit var root:View

    var id : Int?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postsViewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel::class.java)
         model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)


        root = inflater.inflate(R.layout.fragment_posts, container, false)
        initiateRecyclerView()

        subscribeObservers()
        return root
    }


    private  fun subscribeObservers(){
        model.id.removeObservers(viewLifecycleOwner)
        model.id.observe(viewLifecycleOwner, Observer {

            id = it
            postsViewModel.getObserver(it!!).removeObservers(viewLifecycleOwner)
            postsViewModel.getObserver(it).observe(viewLifecycleOwner, Observer {
                if(it != null){
                    Log.e("qmr","${it.data!!}")
                    when(it.status){
                        Resource.AuthStatus.LOADING -> showProgressBar(true)
                        Resource.AuthStatus.SUCCESS -> {showProgressBar(false)
                        postRecyclerAdapter.setPosts(it.data as List<Post>)}
                        Resource.AuthStatus.ERROR -> {showProgressBar(false)
                        Log.e("qmrError",it.message!!)
                        }
                    }
            }})

            Log.e("Qmr","$id")
        })


    }
    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible)
            root.progress_bar.visibility = View.VISIBLE
        else
            root.progress_bar.visibility = View.GONE

    }



    private fun initiateRecyclerView(){
        root.recycler_view.layoutManager = LinearLayoutManager(context)
        val verticalSpaceItemDecoration =VerticalSpaceItemDecoration(15)
        root.recycler_view.addItemDecoration(verticalSpaceItemDecoration)
        root.recycler_view.adapter = postRecyclerAdapter

    }
}