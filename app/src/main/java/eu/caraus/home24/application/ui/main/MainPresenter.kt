package eu.caraus.home24.application.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent


class MainPresenter( val navigator  : MainContract.Navigator   ) : MainContract.Presenter {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        navigator.showSelection()
    }

    override fun goBack(): Boolean {
        return navigator.goBack()
    }

    override fun onViewAttached( view: MainContract.View) {}

    override fun onViewDetached( detach: Boolean) {}

}