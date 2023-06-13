package ru.kapuchinka.kinosklad.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.kapuchinka.kinosklad.api.service.CategoryApi
import ru.kapuchinka.kinosklad.api.service.FeedbackApi
import ru.kapuchinka.kinosklad.api.service.FilmApi

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://193.233.49.143/")
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
}