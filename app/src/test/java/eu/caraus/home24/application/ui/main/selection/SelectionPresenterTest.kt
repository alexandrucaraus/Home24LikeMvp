package eu.caraus.home24.application.ui.main.selection

import eu.caraus.home24.application.AppModule
import eu.caraus.home24.application.common.Configuration
import eu.caraus.home24.application.common.schedulers.TestSchedulerProvider
import eu.caraus.home24.application.data.domain.home24.ArticlesItem
import io.mockk.*

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
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

        assertThat( presenter.getItemsLiked() , `is`(0) )
        assertThat( presenter.getItemsCount() , `is`(0) )
    }

    @Test
    fun likeArticle() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot )) }

        assertThat( presenter.getItemsLiked() , `is`(0))
        assertThat( presenter.getItemsCount() , `is`(0))

        assertThat( presenter.articles.size, `is`( Configuration.NUMBER_OF_ITEMS_TO_REVIEW))

        presenter.likeArticle()

        verify { view.showArticle( capture( slot )) }

        assertThat( presenter.getItemsLiked() , `is`(1))
        assertThat( presenter.getItemsCount() , `is`(1))

        presenter.likeArticle()

        verify { view.showArticle( capture( slot )) }

        assertThat( presenter.getItemsLiked() , `is`(2))
        assertThat( presenter.getItemsCount() , `is`(2))

    }

    @Test
    fun dislikeArticle() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.getItemsLiked() , `is`(0))
        assertThat( presenter.getItemsCount() , `is`(0))

        presenter.disLikeArticle()

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.getItemsLiked(), `is`(0))
        assertThat( presenter.getItemsCount(), `is`(1))

        presenter.disLikeArticle()

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.getItemsLiked(), `is`(0))
        assertThat( presenter.getItemsCount(), `is`(2))

        presenter.disLikeArticle()

        verify { view.showArticle( capture( slot) ) }

        assertThat( presenter.getItemsLiked(), `is`(0))
        assertThat( presenter.getItemsCount(), `is`(3))

    }

    @Test
    fun likeAllArticles() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot )) }

        assertThat( presenter.articles.size, `is`( Configuration.NUMBER_OF_ITEMS_TO_REVIEW))

        for( i in 1..( Configuration.NUMBER_OF_ITEMS_TO_REVIEW - 1 ) ) {

            presenter.likeArticle()

            verify { view.showArticle( capture(slot)) }

            assertThat( presenter.getItemsLiked() , `is`(i))
            assertThat( presenter.getItemsCount() , `is`(i+1))

        }

        presenter.likeArticle()

        verify { view.hideArticle() }
        verify { view.showReview()  }

        assertThat( presenter.getItemsLiked() , `is`(10 ))
        assertThat( presenter.getItemsCount() , `is`(10 ))

        presenter.likeArticle()

        verify { view.hideArticle() }
        verify { view.showReview()  }

        assertThat( presenter.getItemsLiked() , `is`(10 ))
        assertThat( presenter.getItemsCount() , `is`(10 ))

    }

    @Test
    fun disLikeAllArticles() {

        var presenter = SelectionPresenter( interactor, navigator , scheduler )

        presenter.onCreate()
        presenter.onViewAttached( view )
        presenter.onResume()

        var slot = slot<ArticlesItem>()

        verify { view.showArticle( capture( slot )) }

        assertThat( presenter.articles.size, `is`( Configuration.NUMBER_OF_ITEMS_TO_REVIEW))

        for( i in 1..( Configuration.NUMBER_OF_ITEMS_TO_REVIEW - 1 ) ) {

            presenter.disLikeArticle()

            verify { view.showArticle( capture(slot)) }

            assertThat( presenter.getItemsLiked() , `is`(0))
            assertThat( presenter.getItemsCount() , `is`(i+1))

        }

        presenter.disLikeArticle()

        verify { view.hideArticle() }
        verify { view.showReview()  }

        assertThat( presenter.getItemsLiked() , `is`(0 ))
        assertThat( presenter.getItemsCount() , `is`(10 ))

        presenter.disLikeArticle()

        verify { view.hideArticle() }
        verify { view.showReview()  }

        assertThat( presenter.getItemsLiked() , `is`(0 ))
        assertThat( presenter.getItemsCount() , `is`(10 ))


    }

}