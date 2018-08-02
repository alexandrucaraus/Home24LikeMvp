package eu.caraus.home24.application

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import eu.caraus.home24.application.ui.main.zdi.MainActivityBuilder

@Singleton
@Component( modules = [

        AndroidSupportInjectionModule::class,

        AppModule::class,

        MainActivityBuilder::class

])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): AppComponent.Builder

        fun build(): AppComponent

    }

    override fun inject(app: App)

}
