package eu.caraus.home24.application.ui.main.review.zdi

import dagger.Subcomponent
import dagger.android.AndroidInjector

import eu.caraus.home24.application.ui.main.review.ReviewFragment


@Subcomponent(modules = [ ReviewModule::class ])
interface ReviewComponent : AndroidInjector<ReviewFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ReviewFragment>()

}