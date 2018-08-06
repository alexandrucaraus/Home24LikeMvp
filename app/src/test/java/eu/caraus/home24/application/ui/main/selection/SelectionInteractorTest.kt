package eu.caraus.home24.application.ui.main.selection

import eu.caraus.dynamo.application.common.retrofit.Outcome
import eu.caraus.home24.application.AppModule
import eu.caraus.home24.application.common.extensions.subOnIoObsOnUi
import eu.caraus.home24.application.common.schedulers.TestSchedulerProvider
import eu.caraus.home24.application.ui.main.zdi.MainActivityModule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class SelectionInteractorTest {


    private val service = AppModule().provideHome24Api()
    private val scheduler = TestSchedulerProvider()

    @Test
    fun interactorTest() {

        var interactor
            =  MainActivityModule().provideSelectionInteractor( service, scheduler )

        interactor.getArticlesOutcome().subOnIoObsOnUi(scheduler).subscribe {

            when( it ) {
                is Outcome.Progress -> {}
                is Outcome.Success  -> assertThat( it.data.size , `is`( not(0)))
                is Outcome.Failure  -> assertEquals( "isGood", "${it.error.message}")

            }

        }

        interactor.getArticles()

        }

}