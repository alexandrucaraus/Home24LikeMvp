package eu.caraus.home24.application.ui.main.zdi

import dagger.Module
import dagger.Provides
import eu.caraus.home24.R
import eu.caraus.home24.application.common.schedulers.SchedulerProvider
import eu.caraus.home24.application.data.remote.home24.Home24Api
import eu.caraus.home24.application.ui.main.MainActivity
import eu.caraus.home24.application.ui.main.MainContract
import eu.caraus.home24.application.ui.main.MainInteractor
import eu.caraus.home24.application.ui.main.MainNavigator
import eu.caraus.home24.application.ui.main.MainPresenter
import eu.caraus.home24.application.ui.main.review.ReviewContract
import eu.caraus.home24.application.ui.main.review.ReviewInteractor
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
    internal fun provideMainPresenter( interactor: MainContract.Interactor,
                                       navigator: MainContract.Navigator): MainContract.Presenter {
        return MainPresenter(interactor, navigator)
    }

    @Provides
    @MainActivityScope
    internal fun provideMainInteractor( service: Home24Api ): MainContract.Interactor {
        return MainInteractor(service)
    }

    @Provides
    @MainActivityScope
    internal fun provideMainNavigator( navigation: MainNavigation): MainContract.Navigator {
        return MainNavigator(navigation)
    }

    // Selection

    @Provides
    @MainActivityScope
    internal fun provideSelectionPresenter( interactor : SelectionContract.Interactor,
                                            navigator  : SelectionContract.Navigator,
                                            scheduler  : SchedulerProvider)
            : SelectionContract.Presenter = SelectionPresenter( interactor, navigator, scheduler )


    @Provides
    @MainActivityScope
    internal fun provideSelectionInteractor( service: Home24Api, scheduler: SchedulerProvider ): SelectionContract.Interactor {
        return SelectionInteractor( service , scheduler )
    }

    @Provides
    @MainActivityScope
    internal fun provideSelectionNavigator( navigation: MainNavigation ) : SelectionContract.Navigator {
        return SelectionNavigator( navigation )
    }

    // Review

    @Provides
    @MainActivityScope
    internal fun provideReviewPresenter(): ReviewContract.Interactor {
        return ReviewInteractor()
    }

    @Provides
    @MainActivityScope
    internal fun provideReviewInteractor(navigation: MainNavigation): ReviewContract.Navigator {
        return ReviewNavigator(navigation)
    }

    @Provides
    @MainActivityScope
    internal fun provideReviewNavigator( activityView: MainActivity): MainNavigation {
        return MainNavigation(activityView, R.id.main_fragment_container)
    }

}
