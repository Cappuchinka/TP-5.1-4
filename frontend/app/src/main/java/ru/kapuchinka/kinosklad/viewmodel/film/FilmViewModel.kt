package ru.kapuchinka.kinosklad.viewmodel.film

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.film.Films
import ru.kapuchinka.kinosklad.repository.film.FilmRepository

class FilmViewModel() : ViewModel() {
    var repository = FilmRepository()
    val filmList: MutableLiveData<Films> = MutableLiveData()

    fun getFilmList() {
        viewModelScope.launch {
            filmList.value = repository.getFilms()
        }
    }

    fun getFilmByCategoryId(cat_id: Int) {
        viewModelScope.launch {
            filmList.value = repository.getFilmsByCategory(cat_id)
        }
    }

}