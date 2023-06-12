package ru.kapuchinka.kinosklad.api.model.film

data class Film(
    val film_id: Int,
    val name: String,
    val description: String,
    val country: String,
    val releaseDate: Int,
    val categories: String
)
