package net.qamar.daggerexample.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.qamar.daggerexample.di.ViewModelKey
import net.qamar.daggerexample.ui.auth.AuthViewModel
import net.qamar.daggerexample.ui.main.ui.home.HomeViewModel
import net.qamar.daggerexample.ui.main.ui.post.PostsViewModel

@Module
abstract class MainViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun getBindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun getBindPostViewModel(postsViewModel: PostsViewModel): ViewModel

}