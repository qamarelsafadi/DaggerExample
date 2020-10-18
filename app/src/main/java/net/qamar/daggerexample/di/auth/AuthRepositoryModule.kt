package net.qamar.daggerexample.di.auth

import android.app.Application
import dagger.Module
import dagger.Provides
import net.qamar.daggerexample.data.remote.auth.AuthApi
import net.qamar.daggerexample.ui.auth.AuthRepository
import net.qamar.daggerexample.util.SessionManager
import retrofit2.Retrofit


@Module
abstract class AuthRepositoryModule {

    companion object {
        @Provides
        fun getAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }

        @Provides
        fun repository(application: Application, authApi: AuthApi , sessionManager: SessionManager): AuthRepository {
            return AuthRepository(
                application,
                authApi,
                sessionManager
            )
        }

    }
}