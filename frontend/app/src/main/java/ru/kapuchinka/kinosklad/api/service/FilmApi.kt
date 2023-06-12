package ru.kapuchinka.kinosklad.api.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.api.model.film.Films

interface FilmApi {
    @GET("get_film_by_id/{film_id}")
    suspend fun getFilmById(@Path("film_id") film_id: Int): Film

    @GET("get_films/")
    suspend fun getFilms(): Films

    @GET("get_films_by_category/{category_id}")
    suspend fun getFilmByCategory(@Path("category_id") category_id: Int): Films

    @GET("get_film/{name}")
    suspend fun getFilmByName(@Path("name") name: String): Film
}