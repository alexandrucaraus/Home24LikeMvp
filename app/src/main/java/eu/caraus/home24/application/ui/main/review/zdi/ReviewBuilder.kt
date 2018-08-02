package eu.caraus.home24.application.ui.main.review.zdi

import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

import eu.caraus.home24.application.ui.main.review.ReviewFragment

@Module(subcomponents = [ ReviewComponent::class ])
abstract class ReviewBuilder {

    @Binds
    @IntoMap
    @FragmentKey(ReviewFragment::class)
    internal abstract fun bindCourseDetails(builder: ReviewComponent.Builder): AndroidInjector.Factory<out Fragment>

}