package ru.kapuchinka.kinosklad.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kapuchinka.kinosklad.api.service.CategoryApi

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
}