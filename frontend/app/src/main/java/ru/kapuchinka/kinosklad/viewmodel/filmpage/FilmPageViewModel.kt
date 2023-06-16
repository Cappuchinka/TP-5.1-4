package ru.kapuchinka.kinosklad.viewmodel.filmpage

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.repository.film.FilmRepository
import ru.kapuchinka.kinosklad.utils.db.DBManager

class FilmPageViewModel() : ViewModel()  {
    var repository = FilmRepository()
    var film: MutableLiveData<Film> = MutableLiveData()
    lateinit var dbManager: DBManager
    var isFavoriteFilm: MutableLiveData<Boolean> = MutableLiveData()

    fun getFilmById(film_id: Int) {
        viewModelScope.launch {
            film.value = repository.getFilmById(film_id)
        }
    }

    fun isFavoriteFilmCheck(context: Context, film_id: Int) {
        dbManager = DBManager(context)
        dbManager.openDB()
        isFavoriteFilm.value = dbManager.isFavoriteFilm(film_id)
        dbManager.closeDB()
    }

    fun isFavoriteFilm() : Boolean {
        return isFavoriteFilm.value!!
    }
}