package com.example.sugardaddy.Helper

import android.database.Cursor
import com.example.sugardaddy.Entity.Film
import com.example.sugardaddy.Entity.User
import com.example.sugardaddy.db.DatabaseContract

object MappingHelper {

    fun mapUserCursorToArrayList(usersCursor: Cursor?): ArrayList<User> {
        val usersList = ArrayList<User>()
        usersCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumn._ID))
                val nama = getString(getColumnIndexOrThrow(DatabaseContract.UserColumn.NAMA))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumn.USERNAME))
                val email = getString(getColumnIndexOrThrow(DatabaseContract.UserColumn.EMAIL))
                val gender = getString(getColumnIndexOrThrow(DatabaseContract.UserColumn.GENDER))
                val birthDate = getString(getColumnIndexOrThrow(DatabaseContract.UserColumn.BIRTH_DATE))
                val password = getString(getColumnIndexOrThrow(DatabaseContract.UserColumn.PASSWORD))
                usersList.add(User(id, nama, username, email, gender, birthDate, password))
            }
        }
        return usersList
    }

    fun mapFilmCursorToArrayList(filmCursor: Cursor?): ArrayList<Film> {
        val filmList = ArrayList<Film>()
        filmCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.FilmColumn.TEMP_ID))
                val judul = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.JUDUL))
                val rating = getDouble(getColumnIndexOrThrow(DatabaseContract.FilmColumn.RATING))
                val tanggalTerbit = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.TANGGAL_TERBIT))
                val actor = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.ACTOR))
                val sinopsis = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.SINOPSIS))
                val genre = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.GENRE))
                val filmType = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.FILM_TYPE))
                val releaseType = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.RELEASE_TYPE))
                val duration = getInt(getColumnIndexOrThrow(DatabaseContract.FilmColumn.DURATION))
                val image = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.IMAGE))
                val imgBackground = getString(getColumnIndexOrThrow(DatabaseContract.FilmColumn.IMG_BACKGRUOND))
                filmList.add(Film(id, judul, rating, tanggalTerbit, actor, sinopsis, genre, filmType, releaseType, duration, image, imgBackground))
            }
        }
        return filmList
    }
}