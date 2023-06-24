package ru.kapuchinka.kinosklad.utils.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import ru.kapuchinka.kinosklad.api.model.favorite.FavoriteFilm

class DBManager(context: Context) {
    val dbHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = dbHelper.writableDatabase
    }

    fun insertToDB(filmId: Int, filmName: String, filmCountry: String, filmYear: Int) {
        val values = ContentValues().apply {
            put(FavoriteDataBaseColumns.COLUMN_FILM_ID, filmId)
            put(FavoriteDataBaseColumns.COLUMN_FILM_NAME, filmName)
            put(FavoriteDataBaseColumns.COLUMN_COUNTRY, filmCountry)
            put(FavoriteDataBaseColumns.COLUMN_YEAR, filmYear)
        }
        db?.insert(FavoriteDataBaseColumns.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readFromDB() : ArrayList<FavoriteFilm> {
        val favoriteFilms = ArrayList<FavoriteFilm>()
        val cursor = db?.query(FavoriteDataBaseColumns.TABLE_NAME, null, null,
            null, null, null, null)
        while (cursor?.moveToNext()!!) {
            val id = cursor.getInt(cursor.getColumnIndex("FavoriteDataBaseColumns.${BaseColumns._ID}"))
            val film_id = cursor.getInt(cursor.getColumnIndex(FavoriteDataBaseColumns.COLUMN_FILM_ID))
            val name = cursor.getString(cursor.getColumnIndex(FavoriteDataBaseColumns.COLUMN_FILM_NAME))
            val country = cursor.getString(cursor.getColumnIndex(FavoriteDataBaseColumns.COLUMN_COUNTRY))
            val year = cursor.getInt(cursor.getColumnIndex(FavoriteDataBaseColumns.COLUMN_YEAR))
            val favoriteFilm = FavoriteFilm(id, film_id, name, country, year)
            favoriteFilms.add(favoriteFilm)
        }
        cursor.close()
        return favoriteFilms
    }

    fun deleteFilmFromDB(favFilmID: Int) {
        db?.execSQL("DELETE FROM ${FavoriteDataBaseColumns.TABLE_NAME} WHERE _id = $favFilmID")
    }

    fun deleteFilmFromDBByFilmID(filmID: Int) {
        db?.execSQL("DELETE FROM ${FavoriteDataBaseColumns.TABLE_NAME} WHERE ${FavoriteDataBaseColumns.COLUMN_FILM_ID} = $filmID")
    }

    fun closeDB() {
        dbHelper.close()
    }

    @SuppressLint("Range")
    fun isFavoriteFilm(film_id: Int) : Boolean {
        val cursor = db?.query(FavoriteDataBaseColumns.TABLE_NAME, null, null,
            null, null, null, null)
        while (cursor?.moveToNext()!!) {
            val id = cursor.getInt(cursor.getColumnIndex(FavoriteDataBaseColumns.COLUMN_FILM_ID))
            if (id == film_id) {
                cursor.close()
                return true
            }
        }
        cursor.close()
        return false
    }
}