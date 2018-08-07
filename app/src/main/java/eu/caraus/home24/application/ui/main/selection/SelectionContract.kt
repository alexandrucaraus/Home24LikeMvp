package eu.caraus.home24.application.ui.main.selection

import android.arch.lifecycle.LifecycleObserver
import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.data.domain.home24.ArticlesItem

import eu.caraus.home24.application.ui.base.BaseContract

import io.reactivex.subjects.PublishSubject

/**
 *  SelectionContract - this is MVP contract implementation for Selection Screen
 *
 */

interface SelectionContract : BaseContract {


    interface Presenter : BaseContract.BasePresenter<View>, LifecycleObserver {

        companion object {
            const val MODE_NONE  = 0
            const val MODE_LEFT  = 1
            const val MODE_RIGHT = 2
        }

        fun likeArticle()
        fun disLikeArticle()

        fun getItemsLiked() : Int
        fun getItemsCount() : Int

        fun isInReview() : Boolean

        fun review()

    }

    interface View : BaseContract.BaseView {

        fun showArticle( article : ArticlesItem, mode : Int )
        fun hideArticle()

        fun showReview()
        fun hideReview()

        fun showLikesCount( likesCount : String )
        fun showTotalCount( totalCount : String )

        fun showLoading()
        fun hideLoading()


        fun showError( error : String)

    }

    interface Interactor {

        fun getArticles()
        fun getArticles( numberOfArticles : Int )
        fun getArticlesOutcome() : PublishSubject<Outcome<List<ArticlesItem?>>>

    }

    interface Navigator {

        fun navigateToReviewScreen( reviewedItems : HashMap<ArticlesItem?,Boolean> )

    }

}