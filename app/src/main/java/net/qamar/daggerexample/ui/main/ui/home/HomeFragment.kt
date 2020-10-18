package net.qamar.daggerexample.ui.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import net.qamar.daggerexample.R
import net.qamar.daggerexample.ui.auth.AuthViewModel
import net.qamar.daggerexample.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this, providerFactory).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}