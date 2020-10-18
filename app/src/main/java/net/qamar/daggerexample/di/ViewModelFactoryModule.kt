package net.qamar.daggerexample.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import net.qamar.daggerexample.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun getViewModelProviderFactory(modelProviderFactory : ViewModelProviderFactory) : ViewModelProvider.Factory
}