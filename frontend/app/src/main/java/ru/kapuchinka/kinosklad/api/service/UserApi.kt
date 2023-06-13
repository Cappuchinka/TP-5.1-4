package ru.kapuchinka.kinosklad.api.service

import retrofit2.http.GET
import retrofit2.http.POST
import ru.kapuchinka.kinosklad.api.model.user.UserPublic

interface UserApi {
    @GET("get_user_by_token/{token}")
    suspend fun getUserByToken(token: String): UserPublic

    @POST("register/")
    suspend fun register()

    @POST("auth/")
    suspend fun auth()
}