package ru.kapuchinka.kinosklad.api.model.favorite

data class FavoriteFilm(
    val filmId: Int,
    val filmName: String,
    val filmCountry: String,
    val filmYear: Int
)
