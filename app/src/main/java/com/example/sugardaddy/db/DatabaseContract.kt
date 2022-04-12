package com.example.sugardaddy.db

import android.provider.BaseColumns

class DatabaseContract {
    internal class UserColumn: BaseColumns{
        companion object {
            const val TABLE_NAME_USER = "user"
            const val _ID = "_id"
            const val NAMA = "nama"
            const val USERNAME = "username"
            const val EMAIL = "email"
            const val GENDER = "gender"
            const val BIRTH_DATE = "birth_date"
            const val PASSWORD = "password"
        }
    }
    internal class FilmColumn: BaseColumns{
        companion object {
            const val TABLE_NAME_FILM = "film"
            const val _ID = "_id"
            const val TEMP_ID = "temp_id"
            const val JUDUL = "judul"
            const val RATING = "rating"
            const val TANGGAL_TERBIT = "tanggal_terbit"
            const val ACTOR = "actor"
            const val SINOPSIS = "sinopsis"
            const val GENRE = "genre"
            const val FILM_TYPE = "film_type"
            const val RELEASE_TYPE = "release_type"
            const val DURATION = "duration"
            const val IMAGE = "image"
            const val IMG_BACKGRUOND = "img_background"
        }
    }
}