package ru.kapuchinka.kinosklad.repository.film

import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.api.model.film.Films

class FilmRepository {
    suspend fun getFilms(): Films {
        return RetrofitInstance.filmApi.getFilms()
    }

    suspend fun getFilm(filmName: String): Film {
        return RetrofitInstance.filmApi.getFilmByName(filmName)
    }

    suspend fun getFilmsByCategory(cat_id: Int): Films {
        return RetrofitInstance.filmApi.getFilmByCategory(cat_id)
    }

    suspend fun getFilmById(film_id: Int): Film {
        return RetrofitInstance.filmApi.getFilmById(film_id)
    }
}