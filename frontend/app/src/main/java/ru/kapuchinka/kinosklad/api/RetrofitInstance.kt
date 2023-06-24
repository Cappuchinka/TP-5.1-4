package ru.kapuchinka.kinosklad.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.kapuchinka.kinosklad.api.service.CategoryApi
import ru.kapuchinka.kinosklad.api.service.FeedbackApi
import ru.kapuchinka.kinosklad.api.service.FilmApi
import ru.kapuchinka.kinosklad.api.service.UserApi

object RetrofitInstance {
    private val loggingInterceptor = LoggingInterceptor()

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://193.233.49.143/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val categoryApi:CategoryApi by lazy {
        retrofit.create(CategoryApi::class.java)
    }

    val filmApi:FilmApi by lazy {
        retrofit.create(FilmApi::class.java)
    }

    val feedbackApi:FeedbackApi by lazy {
        retrofit.create(FeedbackApi::class.java)
    }

    val userApi:UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
}