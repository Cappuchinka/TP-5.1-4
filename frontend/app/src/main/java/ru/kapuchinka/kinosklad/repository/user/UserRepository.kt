package ru.kapuchinka.kinosklad.repository.user

import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.api.model.user.Token
import ru.kapuchinka.kinosklad.api.model.user.UserPublic

class UserRepository {
    suspend fun getUserByToken(token: String): UserPublic {
        return RetrofitInstance.userApi.getUserByToken(token)
    }

    suspend fun auth(authData: LoginUser) : Token {
        return RetrofitInstance.userApi.auth(authData)
    }
}