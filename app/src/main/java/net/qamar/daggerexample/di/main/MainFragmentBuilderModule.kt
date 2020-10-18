package net.qamar.daggerexample.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.qamar.daggerexample.ui.main.ui.home.HomeFragment
import net.qamar.daggerexample.ui.main.ui.post.PostsFragment

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector()
    abstract fun contributePostsFragment(): PostsFragment
}