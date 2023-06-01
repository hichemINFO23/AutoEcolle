package com.example.autoecole.global

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(SqlTable.admin)
        db?.execSQL(SqlTable.student)
        db?.execSQL(SqlTable.moniteur)
        db?.execSQL(SqlTable.paiement)
        db?.execSQL(SqlTable.session)



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun executeQuery(sql: String): Boolean {
        try {
            val database = this.writableDatabase
            database.execSQL(sql)

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun fireQuery(sql: String): Cursor? {
        var temCursor: Cursor? = null
        try {
            val database = this.writableDatabase
            temCursor = database.rawQuery(sql, null)
            if (temCursor != null && temCursor.count > 0) {
                temCursor.moveToFirst()
                return temCursor
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return temCursor
    }
    // Add the following function to your DB class

    fun updateAdminPassword(oldPassword: String, newPassword: String): Boolean {
        val sqlQuery = "SELECT * FROM ADMIN WHERE PASSWORD = '$oldPassword'"
        val cursor = fireQuery(sqlQuery)
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            val adminId = cursor.getInt(cursor.getColumnIndex("ID"))
            val updateQuery = "UPDATE ADMIN SET PASSWORD = '$newPassword' WHERE ID = $adminId"
            return executeQuery(updateQuery)
        }
        return false
    }
    fun executeQueryWithArgs(sql: String, args: Array<String>): Boolean {
        try {
            val database = this.writableDatabase
            database.execSQL(sql, args)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }




    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "AutoSchool.DB"
    }
}