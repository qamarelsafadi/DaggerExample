package net.qamar.daggerexample.di
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.qamar.daggerexample.util.BaseApplication
import net.qamar.daggerexample.util.SessionManager
import javax.inject.Singleton
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class , ActivityBuilderModule::class , AppModule::class ,
    ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {


    fun sessionManager():SessionManager
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application) : Builder
    }
}