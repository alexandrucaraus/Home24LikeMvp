package eu.caraus.home24.application.ui.main.selection.zdi

import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

import eu.caraus.home24.application.ui.main.selection.SelectionFragment

@Module(subcomponents = [SelectionComponent::class])
abstract class SelectionBuilder {

    @Binds
    @IntoMap
    @FragmentKey(SelectionFragment::class)
    internal abstract fun bindCourseList(builder: SelectionComponent.Builder): AndroidInjector.Factory<out Fragment>


}
