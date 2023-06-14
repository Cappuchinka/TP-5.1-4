package ru.kapuchinka.kinosklad.api.model.user

import com.google.gson.annotations.SerializedName

data class UserPublic(
    val email: String,
    val nickname: String
)
