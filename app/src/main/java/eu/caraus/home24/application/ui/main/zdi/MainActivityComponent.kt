package eu.caraus.home24.application.ui.main.zdi

import dagger.Subcomponent
import dagger.android.AndroidInjector
import eu.caraus.home24.application.ui.main.MainActivity
import eu.caraus.home24.application.ui.main.review.zdi.ReviewBuilder
import eu.caraus.home24.application.ui.main.selection.zdi.SelectionBuilder

@MainActivityScope
@Subcomponent(modules = [

        MainActivityModule::class,
        SelectionBuilder::class,
        ReviewBuilder::class

])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}
