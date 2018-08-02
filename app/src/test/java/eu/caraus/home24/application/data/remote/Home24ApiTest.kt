package eu.caraus.home24.application.data.remote

import eu.caraus.home24.application.data.remote.home24.Home24Api
import eu.caraus.home24.application.data.remote.home24.Home24ApiClient
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Home24ApiTest {

    private val home24Api : Home24Api? =  Home24ApiClient().client.create( Home24Api::class.java )

    @Test
    fun fetchDataTest () {

        home24Api?.articles
                 ?.subscribe({
                     assertThat( it.embedded?.articles?.size, `is`( 10 ))
                 },{
                     assertEquals("isSuccessful","isFailed ${it.message}")
                 })


    }

    @Test
    fun fetchMoreDataTest() {

        home24Api?.getArticles(27830, 5)
                 ?.subscribe({


                     assertThat( it.embedded?.articles?.size, `is`( 4 ))


                 },{
                     assertEquals("isSuccessful","isFailed ${it.message}")
                 })
    }


    @Test
    fun fetchDataNegativeTest () {

        home24Api?.articles
                ?.subscribe({

                    assertThat( it.embedded?.articles?.size, `is`( 10 ))

                },{
                    assertEquals("isSuccessful","isFailed ${it.message}")
                })

    }

}