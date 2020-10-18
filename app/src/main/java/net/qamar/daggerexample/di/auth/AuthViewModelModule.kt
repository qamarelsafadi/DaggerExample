package net.qamar.daggerexample.di.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.qamar.daggerexample.di.ViewModelKey
import net.qamar.daggerexample.ui.auth.AuthViewModel

@Module
abstract class AuthViewModelModule {

@Binds
@IntoMap
@ViewModelKey(AuthViewModel::class)
abstract fun getBindAuthViewModel(authViewModel: AuthViewModel):ViewModel

}