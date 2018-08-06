package eu.caraus.home24.application.ui.main.review

/**
 *  Presenter implementation for the Review screen
 */

class ReviewPresenter ( val navigator  : ReviewContract.Navigator ) : ReviewContract.Presenter {

    override fun goBack() {
        navigator.goBack()
    }

    override fun onViewAttached( view: ReviewContract.View ) {}
    override fun onViewDetached( detach: Boolean ) {}

}