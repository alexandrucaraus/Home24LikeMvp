package eu.caraus.home24.application.data.remote.home24


import eu.caraus.home24.application.data.domain.home24.Home24ApiData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Home24Api service interface for Retrofit
 *
 */

interface Home24Api {

    @get:GET("/api/v1/articles?appDomain=1&locale=de_DE&offset=0&limit=10")
    val articles: Flowable<Home24ApiData>

    @GET("/api/v1/articles?appDomain=1&locale=de_DE")
    fun getArticles( @Query("offset") offset : Int ,
                     @Query("limit")   limit : Int ): Flowable<Home24ApiData>


}
