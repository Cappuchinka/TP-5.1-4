package ru.kapuchinka.kinosklad.viewmodel.filmpage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.repository.film.FilmRepository

class FilmPageViewModel() : ViewModel()  {
    var repository = FilmRepository()
    var film: MutableLiveData<Film> = MutableLiveData()

    fun getFilmById(film_id: Int) {
        viewModelScope.launch {
            film.value = repository.getFilmById(film_id)
        }
    }
}