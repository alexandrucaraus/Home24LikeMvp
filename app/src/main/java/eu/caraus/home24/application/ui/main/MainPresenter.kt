package eu.caraus.home24.application.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent


class MainPresenter(val interactor : MainContract.Interactor,
                    val navigator  : MainContract.Navigator   ) : MainContract.Presenter {

    private var view : MainContract.View? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        navigator.showCourseList()
    }

    override fun onBack(): Boolean {
        return navigator.goBack()
    }

    override fun onViewAttached( view: MainContract.View) {
        this.view = view
    }

    override fun onViewDetached( detach: Boolean) {
        this.view = null
    }

}