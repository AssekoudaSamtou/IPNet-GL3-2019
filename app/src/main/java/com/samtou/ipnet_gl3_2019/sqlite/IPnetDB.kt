package com.samtou.ipnet_gl3_2019.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class IPnetDB(val context: Context, val dbName: String, dbVersion: Int): SQLiteOpenHelper(context, dbName, null, dbVersion) {

    companion object {
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val IMG = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table animal(id integer primary key autoincrement, name text, description text, image blob)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}