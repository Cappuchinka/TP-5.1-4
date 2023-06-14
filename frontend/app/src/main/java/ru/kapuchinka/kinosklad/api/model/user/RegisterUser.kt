package ru.kapuchinka.kinosklad.api.model.user

data class RegisterUser(
    val nickname: String,
    val email: String,
    val password: String
)
