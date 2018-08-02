package eu.caraus.home24.application.ui.main.selection

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.common.extensions.subOnIoObsOnUi

import eu.caraus.home24.application.common.schedulers.SchedulerProvider
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import io.reactivex.disposables.Disposable

class SelectionPresenter( val interactor : SelectionContract.Interactor,
                          val navigator  : SelectionContract.Navigator,
                          private val scheduler  : SchedulerProvider ) : SelectionContract.Presenter {

    companion object {
        const val REVIEW_ITEM_COUNT = 5
    }

    private var view : SelectionContract.View? = null

    private var disposable : Disposable? = null

    private val articles = mutableListOf<ArticlesItem?>()
    private val reviewedArticles = hashMapOf<ArticlesItem?,Boolean>()

    private var itemsViewed = 0
            get() = field

    private var itemsLiked  = 0
            get() = field


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

        disposable = interactor.getArticlesOutcome().subOnIoObsOnUi(scheduler).subscribe {

            when( it ) {

                is Outcome.Progress ->  if ( it.loading ) showLoading() else hideLoading()

                is Outcome.Success  ->  {

                    val showData = articles.size == 0

                    articles.addAll( it.data )

                    if( showData ) showArticle()

                }

                is Outcome.Failure  ->  showError()

            }

        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        interactor.getArticles()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable?.dispose()
    }

    override fun onViewAttached( view : SelectionContract.View ) {
        this.view = view
    }

    override fun onViewDetached(detach: Boolean) {
        this.view = null
    }

    override fun likeArticle( article: ArticlesItem ) {

        reviewedArticles[ article ] = true

        itemsLiked++

        showArticle()

    }

    override fun disLikeArticle(article: ArticlesItem) {

        reviewedArticles[ article ] = false

        showArticle()

    }

    private fun showArticle() {

        if( itemsViewed == articles.size - 2 ){
            interactor.getArticles()
        }

        view?.showLikesCount( "$itemsLiked" )
        view?.showTotalCount( "$itemsViewed")

        articles[ itemsViewed++ ]?.let { view?.showArticle( it ) }

        if( itemsViewed >= REVIEW_ITEM_COUNT ){
            view?.activateReview()
        } else {
            view?.deactivateReview()
        }

    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }

    private fun showError(){

    }

    override fun review() {
        navigator.navigateToReviewScreen( reviewedArticles )
    }

}