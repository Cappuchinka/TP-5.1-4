package ru.kapuchinka.kinosklad.viewmodel.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.repository.film.FilmRepository

class SearchViewModel() : ViewModel() {
    var repository = FilmRepository()
    val searchList: MutableLiveData<Film> = MutableLiveData()

    fun getFilmsByName(filmName: String) {
        viewModelScope.launch {
            searchList.value = repository.getFilm(filmName)
        }
    }
}