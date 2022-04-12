package com.example.sugardaddy.Helper

import android.database.Cursor
import com.example.sugardaddy.Entity.User
import com.example.sugardaddy.db.DatabaseContract

object MappingHelper {

    fun mapCursorToArrayList(usersCursor: Cursor?): ArrayList<User> {
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
}