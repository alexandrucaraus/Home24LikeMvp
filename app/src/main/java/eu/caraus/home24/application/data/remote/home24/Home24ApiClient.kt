package eu.caraus.home24.application.data.remote.home24

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Home24ApiClient {

    companion object{
        const val BASE_URL = "https://api-mobile.home24.com/"
    }

    val client : Retrofit

    init {

        val interceptor = HttpLoggingInterceptor().apply {
             //level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor( interceptor ).build()

        this.client = Retrofit.Builder().baseUrl( BASE_URL )
                              .addConverterFactory( GsonConverterFactory.create())
                              .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                              .client( client)
                              .build()
    }

}
