package ru.kapuchinka.kinosklad.utils.db

import android.provider.BaseColumns

object FavoriteDataBaseColumns : BaseColumns {
    const val TABLE_NAME = "favorite_films"
    const val COLUMN_FILM_ID = "film_id"
    const val COLUMN_FILM_NAME = "film_name"
    const val COLUMN_COUNTRY = "country"
    const val COLUMN_YEAR = "year"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "FavoriteFilms.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_FILM_ID INTEGER," +
                "$COLUMN_FILM_NAME TEXT," +
                "$COLUMN_COUNTRY TEXT," +
                "$COLUMN_YEAR INTEGER" +
            ")"

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}