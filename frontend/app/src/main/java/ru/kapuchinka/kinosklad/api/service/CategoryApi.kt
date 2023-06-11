package ru.kapuchinka.kinosklad.api.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.api.model.category.Categories

interface CategoryApi {
    @GET("get_category_info/{category_id}")
    suspend fun getCategoryById(@Path("category_id") category_id: Int): Film

    @GET("get_categories/")
    suspend fun getCategories(): Categories
}