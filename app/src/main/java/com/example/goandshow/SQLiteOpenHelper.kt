package com.example.goandshow

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, "userInfo", null, 1) {

    private val craeteUserAccount = "CREATE TABLE user_account (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_name TEXT," +
            "user_email TEXT," +
            "user_password TEXT" +
            ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(craeteUserAccount)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 在此方法中处理数据库升级逻辑
        db.execSQL("drop table if exists user_account")
        onCreate(db)
    }
}
