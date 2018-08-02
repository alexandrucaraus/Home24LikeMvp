package eu.caraus.home24.application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import eu.caraus.home24.application.common.schedulers.AppSchedulerProvider
import eu.caraus.home24.application.common.schedulers.SchedulerProvider

import eu.caraus.home24.application.data.remote.home24.Home24Api
import eu.caraus.home24.application.data.remote.home24.Home24ApiClient
import eu.caraus.home24.application.ui.main.zdi.MainActivityComponent

@Module( subcomponents = [

    MainActivityComponent::class

])
class AppModule {

    @Provides
    @Singleton
    fun provideHome24Api() : Home24Api = Home24ApiClient().client.create( Home24Api::class.java )

    @Provides
    @Singleton
    fun providesScheduler() : SchedulerProvider = AppSchedulerProvider()


}
