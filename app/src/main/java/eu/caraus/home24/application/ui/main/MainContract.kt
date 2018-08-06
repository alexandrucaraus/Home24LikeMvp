package eu.caraus.home24.application.ui.main

import android.arch.lifecycle.LifecycleObserver

import eu.caraus.home24.application.ui.base.BaseContract

/**
 *  Interface definitions for the MainActivity, which are part of MVP pattern
 */

interface MainContract : BaseContract {

    interface Presenter : BaseContract.BasePresenter<View>, LifecycleObserver {
        fun goBack() : Boolean
    }

    interface View : BaseContract.BaseView

    interface Navigator {

        fun showSelection()

        fun goBack() : Boolean

    }

}