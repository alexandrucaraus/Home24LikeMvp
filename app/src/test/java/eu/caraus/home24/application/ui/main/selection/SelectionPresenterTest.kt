package eu.caraus.home24.application.ui.main.selection

import eu.caraus.home24.application.AppModule
import eu.caraus.home24.application.common.schedulers.TestSchedulerProvider
import eu.caraus.home24.application.data.domain.home24.ArticlesItem

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

import org.junit.Test


class SelectionPresenterTest {

    private val service    = AppModule().provideHome24Api()
    private val scheduler  = TestSchedulerProvider()

    private var interactor =  SelectionInteractor( service, scheduler )

    private val navigator  = mockk<SelectionNavigator>()
    private var view       = mockk<SelectionContract.View>( relaxed = true )

    @Test
    fun showFirstArticle() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot )) }

        assertThat( presenter.itemsLiked , `is`(0) )
        assertThat( presenter.itemsViewed, `is`(1) )
    }

    @Test
    fun likeArticle() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.itemsViewed , `is`(1))

        assertThat( presenter.articles.size, `is`(10))

        presenter.likeArticle( slot.captured )

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.itemsLiked ,  `is`(1))
        assertThat( presenter.itemsViewed , `is`(2))

    }

    @Test
    fun dislikeArticle() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.itemsViewed , `is`(1) )

        presenter.disLikeArticle( slot.captured )

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.itemsLiked ,  `is`(0))
        assertThat( presenter.itemsViewed , `is`(2))

    }

    @Test
    fun likeArticleManiTaimz() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot) ) }
        assertThat( presenter.itemsViewed , `is`(1))

        for( i in 2..100 ) {
            presenter.likeArticle(slot.captured)
            verify { view.showArticle(capture(slot)) }
            assertThat( presenter.itemsLiked  , `is`(i-1))
            assertThat( presenter.itemsViewed , `is`( i ))
        }

    }

}