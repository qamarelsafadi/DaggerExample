package net.qamar.daggerexample.ui.main.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import net.qamar.daggerexample.R

class PostsFragment : Fragment() {

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postsViewModel =
            ViewModelProviders.of(this).get(PostsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_posts, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        postsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}