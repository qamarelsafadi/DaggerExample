package net.qamar.daggerexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.qamar.daggerexample.di.auth.AuthRepositoryModule
import net.qamar.daggerexample.di.auth.AuthViewModelModule
import net.qamar.daggerexample.di.main.MainFragmentBuilderModule
import net.qamar.daggerexample.di.main.MainRepositoryModule
import net.qamar.daggerexample.di.main.MainViewModelModule
import net.qamar.daggerexample.ui.auth.AuthActivity
import net.qamar.daggerexample.ui.main.MainActivity


//only activity contribution
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class  , AuthRepositoryModule::class])
    abstract fun contributeAuthActivity(): AuthActivity


    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class , MainViewModelModule::class ,
        MainRepositoryModule::class])
    abstract fun contributeMainActivity(): MainActivity


}