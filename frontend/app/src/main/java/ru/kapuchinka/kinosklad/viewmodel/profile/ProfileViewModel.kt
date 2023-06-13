package ru.kapuchinka.kinosklad.viewmodel.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.repository.user.UserRepository

class ProfileViewModel : ViewModel() {
    var repository = UserRepository()
    val token: MutableLiveData<String> = MutableLiveData()

    fun login(authData: LoginUser) {
        viewModelScope.launch {
            token.value = repository.auth(authData).token
        }
    }
}