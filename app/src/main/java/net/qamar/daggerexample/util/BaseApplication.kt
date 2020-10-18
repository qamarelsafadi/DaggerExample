package net.qamar.daggerexample.util

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.qamar.daggerexample.di.AppComponent
import net.qamar.daggerexample.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {

        return DaggerAppComponent.builder().application(this).build()
    }
}