package eu.caraus.home24.application.ui.base

interface BaseContract {

    interface BasePresenter<T : BaseContract.BaseView> {

        fun onViewAttached(view: T)
        fun onViewDetached(detach: Boolean)

    }

    interface BaseView

}