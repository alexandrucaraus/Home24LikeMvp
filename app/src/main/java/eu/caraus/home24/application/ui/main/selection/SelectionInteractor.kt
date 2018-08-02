package eu.caraus.home24.application.ui.main.selection

import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.common.extensions.*
import eu.caraus.home24.application.common.schedulers.SchedulerProvider
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import eu.caraus.home24.application.data.remote.home24.Home24Api
import io.reactivex.subjects.PublishSubject


class SelectionInteractor( private val home24Api : Home24Api ,
                           private val scheduler : SchedulerProvider ) : SelectionContract.Interactor {


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
                    dataFetchResult.failed(it)
                })

    }

    override fun getArticlesOutcome() : PublishSubject<Outcome<List<ArticlesItem?>>>{
        return dataFetchResult
    }


}