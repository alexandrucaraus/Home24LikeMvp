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
import eu.caraus.home24.application.ui.main.znav.MainScreenFlow
import io.reactivex.disposables.CompositeDisposable


@Module
class MainActivityModule {

    // Main

    @Provides
    @MainActivityScope
    internal fun provideMainScreenFlow( activityView: MainActivity )
            : MainScreenFlow = MainScreenFlow( activityView, R.id.main_fragment_container)

    @Provides
    @MainActivityScope
    internal fun provideMainPresenter( navigator: MainContract.Navigator)
            : MainContract.Presenter = MainPresenter( navigator )


    @Provides
    @MainActivityScope
    internal fun provideMainNavigator( screenFlow: MainScreenFlow )
            : MainContract.Navigator = MainNavigator( screenFlow )


    // Selection

    @Provides
    @MainActivityScope
    internal fun provideSelectionPresenter( interactor : SelectionContract.Interactor,
                                            navigator  : SelectionContract.Navigator,
                                            scheduler  : SchedulerProvider,
                                            compositeDisposable: CompositeDisposable)
            : SelectionContract.Presenter = SelectionPresenter( interactor, navigator, scheduler ,compositeDisposable)


    @Provides
    @MainActivityScope
    internal fun provideSelectionInteractor( service: Home24Api,
                                             scheduler: SchedulerProvider,
                                             compositeDisposable: CompositeDisposable)
            : SelectionContract.Interactor = SelectionInteractor( service , scheduler , compositeDisposable)


    @Provides
    @MainActivityScope
    internal fun provideSelectionNavigator( screenFlow: MainScreenFlow )
            : SelectionContract.Navigator = SelectionNavigator( screenFlow )


    // Review

    @Provides
    @MainActivityScope
    internal fun provideReviewNavigator(screenFlow: MainScreenFlow)
            : ReviewContract.Navigator = ReviewNavigator(screenFlow)


    @Provides
    @MainActivityScope
    internal fun provideCompositeDisposable() = CompositeDisposable()

}
