package ru.kapuchinka.kinosklad.repository.user

import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.user.UserPublic

class UserRepository {
    suspend fun getUserByToken(token: String): UserPublic {
        return RetrofitInstance.userApi.getUserByToken(token)
    }
}