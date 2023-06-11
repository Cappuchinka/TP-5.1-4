package ru.kapuchinka.kinosklad.api.model

data class Film(
    val film_id: Int,
    val film_name: String,
    val description: String,
    val country: String,
    val year: Int,
    val categories: List<Film>
)
