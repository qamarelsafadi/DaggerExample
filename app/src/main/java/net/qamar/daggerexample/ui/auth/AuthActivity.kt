package net.qamar.daggerexample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import net.qamar.daggerexample.R
import net.qamar.daggerexample.models.Resource
import net.qamar.daggerexample.ui.main.MainActivity
import net.qamar.daggerexample.viewmodels.ViewModelProviderFactory
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var hello: String

    @Inject
    @Named("is_null")
    lateinit var appIsNull: String

    // if the variable is a primitive one you need to inject you variable into his set
    @set:Inject
    var isAppNull: Boolean? = null

    @Inject
    lateinit var myDrawable: Drawable

    @Inject
    lateinit var requestManager: RequestManager


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        getMyLogo()
        observer()
        login_button.setOnClickListener {
            login()
        }
    }

    private fun getMyLogo() {
        requestManager
            .load(myDrawable)
            .into(login_logo)
    }

    private fun login() {
        if (TextUtils.isEmpty(user_id_input.text.toString()))
            return
        viewModel.authWithID(user_id_input.text.toString().toInt())
    }

    private fun observer() {
        viewModel.observerUser().observe(this, Observer {
            if (it != null){
                when (it.status) {
                    Resource.AuthStatus.LOADING -> showProgressBar(true)
                    Resource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.e("qmrAuthenticated", it.data!!.email!!)
                        onLoginSuccess()
                    }
                    Resource.AuthStatus.NOT_AUTHENTICATED -> showProgressBar(false)
                    Resource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Log.e("qmrError", it.message!!)

                    }
                    Resource.AuthStatus.SUCCESS -> showProgressBar(false)
                }
            }
        })
    }


    private fun onLoginSuccess(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.GONE

    }


}