package ru.kapuchinka.kinosklad.utils.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, FavoriteDataBaseColumns.DATABASE_NAME,
    null, FavoriteDataBaseColumns.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val request = FavoriteDataBaseColumns.CREATE_TABLE
        db?.execSQL(request)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(FavoriteDataBaseColumns.DROP_TABLE)
        onCreate(db)
    }

}