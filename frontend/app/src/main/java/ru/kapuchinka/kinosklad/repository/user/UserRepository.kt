package ru.kapuchinka.kinosklad.repository.user

import android.util.Log
import retrofit2.Response
import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.api.model.user.RegisterUser
import ru.kapuchinka.kinosklad.api.model.user.Token
import ru.kapuchinka.kinosklad.api.model.user.UserPublic

class UserRepository {
    suspend fun getUserByToken(token: String): Response<UserPublic> {
        return RetrofitInstance.userApi.getUserByToken(token)
    }

    suspend fun auth(authData: LoginUser) : Response<Token> {
        val token = RetrofitInstance.userApi.auth(authData)
        return token
    }

    suspend fun register(registerData: RegisterUser) : Response<Token> {
        return RetrofitInstance.userApi.register(registerData)
    }
}