package eu.caraus.home24.application.ui.main.selection

import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.common.extensions.*
import eu.caraus.home24.application.common.schedulers.SchedulerProvider
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.data.remote.home24.Home24Api
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 *  SelectionInteractor - this class queries the Home24Api service,
 *  and the publishes requested data down the pipe
 *
 */

class SelectionInteractor( private val home24Api : Home24Api ,
                           private val scheduler : SchedulerProvider,
                           private val compositeDisposable: CompositeDisposable) : SelectionContract.Interactor {

    private val dataFetchResult = PublishSubject.create<Outcome<List<ArticlesItem?>>>()

    private var offset = 0
    private var limit  = 10

    override fun getArticles()  {

        dataFetchResult.loading(true )

        home24Api.getArticles( offset , limit ).subOnIoObsOnUi( scheduler ).subscribe(
                {
                    it.embedded?.articles?.let {
                        offset = limit ; limit += limit
                        dataFetchResult.success( it )
                    }
                },
                {
                    dataFetchResult.failed( it )
                }
        ).addTo( compositeDisposable )

    }

    override fun getArticles( numberOfArticles: Int ) {

        dataFetchResult.loading(true )

        home24Api.getArticles( 0 , numberOfArticles ).subOnIoObsOnUi( scheduler).subscribe(
                {
                    it.embedded?.articles?.let {
                        dataFetchResult.success( it )
                    }
                },
                {
                    dataFetchResult.failed( it )
                }
        ).addTo( compositeDisposable )

    }

    override fun getArticlesOutcome() : PublishSubject<Outcome<List<ArticlesItem?>>>{
        return dataFetchResult
    }


}