package com.example.sugardaddy.db

import android.provider.BaseColumns

class DatabaseContract {
    internal class UserColumn: BaseColumns{
        companion object {
            const val TABLE_NAME = "user"
            const val _ID = "_id"
            const val NAMA = "nama"
            const val USERNAME = "username"
            const val EMAIL = "email"
            const val GENDER = "gender"
            const val BIRTH_DATE = "birth_date"
            const val PASSWORD = "password"
        }
    }
}