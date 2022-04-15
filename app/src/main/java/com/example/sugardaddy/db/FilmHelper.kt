package com.example.sugardaddy.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.TABLE_NAME_FILM

class FilmHelper(context: Context) {
    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE_FILM = TABLE_NAME_FILM
        private var INSTANCE: FilmHelper? = null
        fun getInstance(context: Context): FilmHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FilmHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE_FILM,
            null,
            null,
            null,
            null,
            null,
            "${DatabaseContract.FilmColumn._ID} ASC",
            null)
    }

    fun queryById(id: String): Cursor {
        return database.query(DATABASE_TABLE_FILM, null, "${DatabaseContract.FilmColumn._ID} = ?", arrayOf(id), null, null, null, null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE_FILM, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE_FILM, values, "${DatabaseContract.FilmColumn._ID} = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE_FILM, "${DatabaseContract.FilmColumn.TEMP_ID} = '$id'", null)
    }

    fun clearAllFilmhelp(){
        dataBaseHelper.clearAllMyList()
    }
}