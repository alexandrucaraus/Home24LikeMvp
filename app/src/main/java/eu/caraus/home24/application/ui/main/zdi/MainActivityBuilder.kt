package eu.caraus.home24.application.ui.main.zdi

import android.app.Activity

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import eu.caraus.home24.application.ui.main.MainActivity
import eu.caraus.home24.application.ui.main.review.zdi.ReviewComponent
import eu.caraus.home24.application.ui.main.selection.zdi.SelectionComponent

@Module( subcomponents = [

        SelectionComponent::class,
        ReviewComponent::class

])
abstract class MainActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}