package eu.caraus.home24.application.ui.main.review.zdi

import dagger.Module
import dagger.Provides
import eu.caraus.home24.application.ui.main.review.ReviewContract
import eu.caraus.home24.application.ui.main.review.ReviewPresenter

@Module
class ReviewModule {

    @Provides
    internal fun providesPresenter(interactor: ReviewContract.Interactor,
                                   navigator: ReviewContract.Navigator): ReviewContract.Presenter {
        return ReviewPresenter(interactor, navigator)
    }

}
