package ru.kapuchinka.kinosklad.api.model.user

import com.google.gson.annotations.SerializedName

data class LoginUser(
    val email: String,
    val password: String
)
