package ru.kapuchinka.kinosklad.api.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.api.model.user.Token
import ru.kapuchinka.kinosklad.api.model.user.UserPublic

interface UserApi {
    @GET("get_user_by_token/{token}")
    suspend fun getUserByToken(@Path("token") token: String): UserPublic

    @POST("register/")
    suspend fun register()

    @POST("auth/")
    suspend fun auth(@Body authRequest: LoginUser) : Response<Token>
}