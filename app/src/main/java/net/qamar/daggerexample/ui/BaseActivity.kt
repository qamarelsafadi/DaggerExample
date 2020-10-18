package net.qamar.daggerexample.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import net.qamar.daggerexample.models.Resource
import net.qamar.daggerexample.ui.auth.AuthActivity
import net.qamar.daggerexample.util.SessionManager
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeObservers()
    }


    private fun subscribeObservers() {
        sessionManager.observerUser().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Resource.AuthStatus.AUTHENTICATED -> Log.e("qmr", it.data!!.email!!)
                    Resource.AuthStatus.ERROR -> Log.e("qmrError", it.message!!)
                    Resource.AuthStatus.NOT_AUTHENTICATED -> goToLoginActivity()
                }
            }
        })
    }


    private fun goToLoginActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}