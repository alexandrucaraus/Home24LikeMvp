package eu.caraus.home24.application.ui.main.selection

import android.arch.lifecycle.LifecycleObserver
import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.data.domain.home24.Home24ApiData
import eu.caraus.home24.application.ui.base.BaseContract
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject


interface SelectionContract : BaseContract {

    interface Presenter : BaseContract.BasePresenter<View>, LifecycleObserver {

        fun likeArticle( article : ArticlesItem )
        fun disLikeArticle( article: ArticlesItem )

        fun review()
    }

    interface View : BaseContract.BaseView {

        fun showArticle( article : ArticlesItem )

        fun showLikesCount( likesCount : String)
        fun showTotalCount( totalLikesCount : String )

        fun activateReview()
        fun deactivateReview()

    }

    interface Interactor {

        fun getArticles()
        fun getArticlesOutcome() : PublishSubject<Outcome<List<ArticlesItem?>>>

    }

    interface Navigator {

        fun navigateToReviewScreen( list: List<ArticlesItem?> , liked : List<String> )

    }

}