package eu.caraus.home24.application.ui.main.review

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent

class ReviewPresenter (val interactor : ReviewContract.Interactor,
                       val navigator  : ReviewContract.Navigator ) : ReviewContract.Presenter {

    var view : ReviewContract.View? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

    }

    override fun goBack() {
        navigator.goBack()
    }

    override fun onViewAttached( view: ReviewContract.View ) {
        this.view = view
    }

    override fun onViewDetached( detach: Boolean ) {
        this.view = null
    }

}