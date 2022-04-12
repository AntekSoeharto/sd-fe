package com.example.sugardaddy.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sugardaddy.db.DatabaseContract.FilmColumn.Companion.TABLE_NAME_FILM
import com.example.sugardaddy.db.DatabaseContract.UserColumn.Companion.TABLE_NAME_USER


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {

    companion object{
        private const val DATABASE_NAME = "dbsdapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME_USER" +
                " (${DatabaseContract.UserColumn._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.UserColumn.NAMA} TEXT," +
                " ${DatabaseContract.UserColumn.USERNAME} TEXT," +
                " ${DatabaseContract.UserColumn.EMAIL} TEXT," +
                " ${DatabaseContract.UserColumn.GENDER} TEXT," +
                " ${DatabaseContract.UserColumn.BIRTH_DATE} TEXT," +
                " ${DatabaseContract.UserColumn.PASSWORD} TEXT)"

        private const val SQL_CREATE_TABLE_FILM = "CREATE TABLE $TABLE_NAME_FILM" +
                " (${DatabaseContract.FilmColumn._ID} INTEGER PRIMARY KEY," +
                " ${DatabaseContract.FilmColumn.JUDUL} TEXT," +
                " ${DatabaseContract.FilmColumn.RATING} DOUBLE," +
                " ${DatabaseContract.FilmColumn.TANGGAL_TERBIT} TEXT," +
                " ${DatabaseContract.FilmColumn.ACTOR} TEXT," +
                " ${DatabaseContract.FilmColumn.SINOPSIS} TEXT," +
                " ${DatabaseContract.FilmColumn.GENRE} TEXT," +
                " ${DatabaseContract.FilmColumn.FILM_TYPE} TEXT," +
                " ${DatabaseContract.FilmColumn.RELEASE_TYPE} TEXT," +
                " ${DatabaseContract.FilmColumn.DURATION} INTEGER," +
                " ${DatabaseContract.FilmColumn.IMAGE} TEXT," +
                " ${DatabaseContract.FilmColumn.IMG_BACKGRUOND} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
        db.execSQL(SQL_CREATE_TABLE_FILM)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_FILM")
        onCreate(db)
    }

    fun clearAll(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM "+ TABLE_NAME_USER);
    }

}