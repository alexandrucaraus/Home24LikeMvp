package eu.caraus.home24.application.ui.main.selection.zdi

import dagger.Subcomponent
import dagger.android.AndroidInjector

import eu.caraus.home24.application.ui.main.selection.SelectionFragment


@Subcomponent(modules = [SelectionModule::class])
interface SelectionComponent : AndroidInjector<SelectionFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SelectionFragment>()
}
