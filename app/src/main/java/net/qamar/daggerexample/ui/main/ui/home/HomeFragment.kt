package net.qamar.daggerexample.ui.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import net.qamar.daggerexample.R
import net.qamar.daggerexample.data.models.Resource
import net.qamar.daggerexample.data.models.User
import net.qamar.daggerexample.ui.main.ui.SharedViewModel
import net.qamar.daggerexample.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var model: SharedViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this, providerFactory).get(HomeViewModel::class.java)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        root = inflater.inflate(R.layout.fragment_home, container, false)
        subscribeObserver()
        return root
    }

    private fun subscribeObserver() {

        homeViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        homeViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            if (it != null)
                when (it.status) {
                    Resource.AuthStatus.AUTHENTICATED -> setUserDetails(it.data!!)
                    Resource.AuthStatus.ERROR -> setErrorDetails(it.message)
                }
        })
    }

    private fun setErrorDetails(message: String?) {
        root.email.text = message

    }

    private fun setUserDetails(data: User) {
        root.email.text = data.email
        root.website.text = data.name
        root.username.text = data.username

        model.sendId(data.id!!)

    }
}