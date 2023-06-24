package ru.kapuchinka.kinosklad.api.model.feedback

data class AddFeedback(
    val film_id: Int,
    val nickname : String,
    val feedback_text : String
)
