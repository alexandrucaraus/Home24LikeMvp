package eu.caraus.home24.application.ui.main.selection

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.common.Configuration
import eu.caraus.home24.application.common.extensions.subOnIoObsOnUi

import eu.caraus.home24.application.common.schedulers.SchedulerProvider
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import io.reactivex.disposables.Disposable

/**
 *  Presenter implementation for the Selection screen
 */

class SelectionPresenter( private val interactor : SelectionContract.Interactor,
                          private val navigator  : SelectionContract.Navigator,
                          private val scheduler  : SchedulerProvider ) : SelectionContract.Presenter {

    private var view : SelectionContract.View? = null

    private var disposable : Disposable? = null

    val articles = mutableListOf<ArticlesItem?>()

    private val reviewedArticles = hashMapOf<ArticlesItem?,Boolean>()

    private var itemsLiked = 0

    private var itemsCount = 0

    private var isInReview = false

    private var isCanShowMore = false

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

        disposable = interactor.getArticlesOutcome().subOnIoObsOnUi(scheduler).subscribe {

            when( it ) {

                is Outcome.Progress ->  if ( it.loading ) showLoading() else hideLoading()

                is Outcome.Success  ->  {

                    articles.addAll( it.data )

                    showArticle()

                }

                is Outcome.Failure  -> showError( "${it.error.message}" )

            }

        }

        interactor.getArticles( Configuration.NUMBER_OF_ITEMS_TO_REVIEW )

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        showArticle()
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

    /**
     *  Method for liking an article
     *
     *  This method :
     *  increments the number of liked articles,
     *  marks the article as liked in the map,
     *  increments the counter of reviewed items
     *
     *  on the last item review is shown
     *
     */
    override fun likeArticle() {

        if( isInReview ){
            view?.hideArticle()
            view?.showReview()
            return
        }

        if( itemsCount <= articles.lastIndex ) {

            if( itemsCount == articles.lastIndex && !isInReview ){

                reviewedArticles[ articles[ itemsCount ] ] = true

                itemsLiked++

                view?.showLikesCount( "${getItemsLiked()}" )

                isInReview = true

                view?.hideArticle()
                view?.showReview()

                return
            }

            reviewedArticles[ articles[ itemsCount ] ] = true

            if( itemsCount < articles.lastIndex ) {
                itemsLiked++
                itemsCount++
                showArticle()
            }

        }

    }

    /**
     *  Method for dis-liking an article
     *
     *  This method :
     *
     *  marks the article as disliked in the map,
     *  increments the counter of reviewed items
     *
     *  on the last item review  button is shown
     *
     */
    override fun disLikeArticle() {

        if( isInReview ){
            view?.hideArticle()
            view?.showReview()
            return
        }

        if( itemsCount <= articles.lastIndex ){

            if( itemsCount == articles.lastIndex && !isInReview ){

                reviewedArticles[ articles[ itemsCount ] ] = false

                isInReview = true

                view?.hideArticle()
                view?.showReview()

                return
            }

            reviewedArticles[ articles[ itemsCount ] ] = false

            if( itemsCount < articles.lastIndex ) {
                itemsCount++
                showArticle()
            }
        }

    }

    /**
     *  method for querying the state of the selection screen
     *  if true  - user can only review rated articles
     *  if false - user can only rate articles
     */
    override fun isInReview() = isInReview

    /**
     *  updates the ui with the article
     */
    private fun showArticle() {

        if( itemsCount  > articles.lastIndex ) return

        view?.showLikesCount( "${getItemsLiked()}" )
        view?.showTotalCount( "${getItemsCount()}" )

        articles[ itemsCount ]?.let { view?.showArticle( it ) }

    }

    /**
     *  returns the number of liked items
     */
    override fun getItemsLiked() = itemsLiked

    /**
     *  returns the number of rated items + one displayed not yet rated
     */
    override fun getItemsCount() = itemsCount + 1

    private fun showLoading() {
        view?.showLoading()
    }

    private fun hideLoading() {
        view?.hideLoading()
    }

    private fun showError( error : String ){
        view?.showError( error )
        view?.hideLoading()
    }

    /**
     *  navigates to the review screen
     */
    override fun review() {
        navigator.navigateToReviewScreen( reviewedArticles )
    }

}