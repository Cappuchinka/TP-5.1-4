package ru.kapuchinka.kinosklad.viewmodel.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.api.model.user.Token
import ru.kapuchinka.kinosklad.api.model.user.UserPublic
import ru.kapuchinka.kinosklad.repository.user.UserRepository

class ProfileViewModel : ViewModel() {
    var repository = UserRepository()
    val token: MutableLiveData<Token> = MutableLiveData()
    val user: MutableLiveData<UserPublic> = MutableLiveData()

    fun login(authData: LoginUser) {
        viewModelScope.launch {
            token.value = repository.auth(authData).body()
        }
    }

    fun getUserByToken(token: String) {
        viewModelScope.launch {
            user.value = repository.getUserByToken(token)
        }
    }
}