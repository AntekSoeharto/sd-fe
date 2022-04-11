package com.example.sugardaddy.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sugardaddy.db.DatabaseContract.UserColumn.Companion.TABLE_NAME


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {

    companion object{
        private const val DATABASE_NAME = "dbuserapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.UserColumn._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.UserColumn.NAMA} TEXT," +
                " ${DatabaseContract.UserColumn.USERNAME} TEXT," +
                " ${DatabaseContract.UserColumn.EMAIL} TEXT," +
                " ${DatabaseContract.UserColumn.GENDER} TEXT," +
                " ${DatabaseContract.UserColumn.BIRTH_DATE} TEXT," +
                " ${DatabaseContract.UserColumn.PASSWORD} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun clearAll(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM "+ TABLE_NAME);
    }

}