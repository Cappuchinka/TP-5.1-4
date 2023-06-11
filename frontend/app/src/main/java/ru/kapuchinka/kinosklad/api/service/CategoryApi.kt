package ru.kapuchinka.kinosklad.api.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.kapuchinka.kinosklad.api.model.Film

interface CategoryApi {
    @GET("get_category_info/{category_id}")
    suspend fun getCategoryById(@Path("category_id") category_id: Int): Film
}