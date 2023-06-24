package ru.kapuchinka.kinosklad.api.model.user

data class User(
    val user_id: Int,
    val nickname: String,
    val email: String,
    val password: String,
    val token: String
)
