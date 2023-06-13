package ru.kapuchinka.kinosklad.api.model.feedback

data class Feedback (
    val feedback_id: Int,
    val film_id: Int,
    val nickname : String,
    val feedback_text : String
)