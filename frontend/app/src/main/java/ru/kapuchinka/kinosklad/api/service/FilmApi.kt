package ru.kapuchinka.kinosklad.api.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.kapuchinka.kinosklad.api.model.film.Film

interface FilmApi {
    @GET("get_films_info/{film_id}")
    suspend fun getFilmById(@Path("film_id") film_id: Int): Film
}