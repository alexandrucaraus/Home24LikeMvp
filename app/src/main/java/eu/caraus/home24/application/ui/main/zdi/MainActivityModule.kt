package eu.caraus.home24.application.ui.main.zdi

import dagger.Module
import dagger.Provides
import eu.caraus.home24.R
import eu.caraus.home24.application.common.schedulers.SchedulerProvider
import eu.caraus.home24.application.data.remote.home24.Home24Api
import eu.caraus.home24.application.ui.main.MainActivity
import eu.caraus.home24.application.ui.main.MainContract
import eu.caraus.home24.application.ui.main.MainNavigator
import eu.caraus.home24.application.ui.main.MainPresenter
import eu.caraus.home24.application.ui.main.review.ReviewContract
import eu.caraus.home24.application.ui.main.review.ReviewNavigator
import eu.caraus.home24.application.ui.main.selection.SelectionContract
import eu.caraus.home24.application.ui.main.selection.SelectionInteractor
import eu.caraus.home24.application.ui.main.selection.SelectionNavigator
import eu.caraus.home24.application.ui.main.selection.SelectionPresenter
import eu.caraus.home24.application.ui.main.znav.MainNavigation


@Module
class MainActivityModule {

    // Main

    @Provides
    @MainActivityScope
    internal fun provideMainNavigation(activityView: MainActivity)
            : MainNavigation = MainNavigation(activityView, R.id.main_fragment_container)

    @Provides
    @MainActivityScope
    internal fun provideMainPresenter( navigator: MainContract.Navigator)
            : MainContract.Presenter = MainPresenter( navigator )


    @Provides
    @MainActivityScope
    internal fun provideMainNavigator( navigation: MainNavigation)
            : MainContract.Navigator = MainNavigator(navigation)


    // Selection

    @Provides
    @MainActivityScope
    internal fun provideSelectionPresenter( interactor : SelectionContract.Interactor,
                                            navigator  : SelectionContract.Navigator,
                                            scheduler  : SchedulerProvider)
            : SelectionContract.Presenter = SelectionPresenter( interactor, navigator, scheduler )


    @Provides
    @MainActivityScope
    internal fun provideSelectionInteractor( service: Home24Api, scheduler: SchedulerProvider )
            : SelectionContract.Interactor = SelectionInteractor( service , scheduler )


    @Provides
    @MainActivityScope
    internal fun provideSelectionNavigator( navigation: MainNavigation )
            : SelectionContract.Navigator = SelectionNavigator( navigation )


    // Review

    @Provides
    @MainActivityScope
    internal fun provideReviewNavigator(navigation: MainNavigation)
            : ReviewContract.Navigator = ReviewNavigator(navigation)


}
