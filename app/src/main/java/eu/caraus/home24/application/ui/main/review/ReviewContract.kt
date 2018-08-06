package eu.caraus.home24.application.ui.main.review

import android.arch.lifecycle.LifecycleObserver
import eu.caraus.home24.application.ui.base.BaseContract

interface ReviewContract : BaseContract {

    interface Presenter : BaseContract.BasePresenter<View>, LifecycleObserver {

        fun goBack()
    }

    interface View : BaseContract.BaseView

    interface Navigator {

        fun goBack()
    }

}