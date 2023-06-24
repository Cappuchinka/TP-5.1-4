package ru.kapuchinka.kinosklad.viewmodel.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kapuchinka.kinosklad.api.model.favorite.FavoriteFilm
import ru.kapuchinka.kinosklad.api.model.favorite.FavoriteFilms
import ru.kapuchinka.kinosklad.utils.db.DBManager

class FavoriteFilmViewModel: ViewModel() {
    val favoriteFilms: MutableLiveData<FavoriteFilms> = MutableLiveData()
    lateinit var dbManager: DBManager

    fun getFavoriteFilms(context: Context) {
        dbManager = DBManager(context)
        dbManager.openDB()
        favoriteFilms.value = FavoriteFilms(dbManager.readFromDB())
    }

    fun deleteFavoriteFilm(context: Context, favFilmID: Int) {
        dbManager = DBManager(context)
        dbManager.openDB()
        dbManager.deleteFilmFromDB(favFilmID)
        dbManager.closeDB()
    }
}