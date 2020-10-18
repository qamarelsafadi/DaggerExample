package net.qamar.daggerexample.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.qamar.daggerexample.di.auth.AuthRepositoryModule
import net.qamar.daggerexample.di.auth.AuthViewModelModule
import net.qamar.daggerexample.ui.auth.AuthActivity
import net.qamar.daggerexample.ui.main.ui.home.HomeFragment
import net.qamar.daggerexample.ui.main.ui.slideshow.PostsFragment

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector()
    abstract fun contributePostsFragment(): PostsFragment
}