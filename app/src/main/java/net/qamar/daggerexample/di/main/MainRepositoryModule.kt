package net.qamar.daggerexample.di.main

import android.app.Application
import dagger.Module
import dagger.Provides
import net.qamar.daggerexample.data.remote.main.MainApi
import net.qamar.daggerexample.ui.main.MainRepository
import net.qamar.daggerexample.ui.main.ui.post.PostRecyclerAdapter
import net.qamar.daggerexample.util.SessionManager
import retrofit2.Retrofit


@Module
abstract class MainRepositoryModule {

    companion object {
        @Provides
        fun adapter(): PostRecyclerAdapter {
            return PostRecyclerAdapter()
        }
        @Provides
        fun getMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

        @Provides
        fun repository(sessionManager: SessionManager , mainApi: MainApi , application: Application): MainRepository {
            return MainRepository(
                sessionManager,
                mainApi,
                application

            )
        }

    }
}