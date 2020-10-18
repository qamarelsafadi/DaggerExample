package net.qamar.daggerexample.di

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import net.qamar.daggerexample.R
import net.qamar.daggerexample.util.BaseApplication
import net.qamar.daggerexample.util.Constants
import net.qamar.ssotexample.util.MoshiArrayListJsonAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

// anything that with exist and not change for the enire application life
@Module
class AppModule {

    companion object {
        @Provides
        fun getString(): String {
            return "Hello Qamar from injected method"
        }

        @Provides
        @Named("is_null")
        fun getApp(application: Application): String {
            return if (application == null)
                "true"
            else
                "false"
        }

        @Provides
        fun isAppNull(application: Application): Boolean {
            return application == null
        }

        @Singleton
        @Provides
        fun getGlideRequestOption(): RequestOptions {
            return RequestOptions.placeholderOf(R.drawable.placeholder)
                .error(R.drawable.placeholder)
        }
        @Singleton
        @Provides
        fun getRequestManager(application: Application , requestOptions: RequestOptions):RequestManager{
            return  Glide.with(application)
                .setDefaultRequestOptions(requestOptions)

        }

        @Singleton
        @Provides
        fun getMyDrawable(application: Application) : Drawable{
            return ContextCompat.getDrawable(application,R.drawable.logodrawable)!!
        }


//        @Singleton
//        @Provides
//        @Named("base_url")
//        fun baseUrl() : String{
//            return Constants.BASE_URL
//        }
//

        @Singleton
        @Provides
        fun getMoshi():Moshi{
            return Moshi.Builder()
                    .add(MoshiArrayListJsonAdapter.FACTORY)
                    .build()!!
        }

        @Singleton
        @Provides
        fun getRetrofitInstance(moshi: Moshi): Retrofit{
            if(moshi!=null)
                Log.e("qmr","hey from Moshi")

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

    }


}