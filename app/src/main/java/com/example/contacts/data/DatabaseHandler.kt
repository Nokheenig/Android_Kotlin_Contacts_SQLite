package com.example.contacts.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.contacts.models.Contact
import com.example.contacts.utils.Constants

class DatabaseHandler (context: Context):
    SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_CONTACTS = "CREATE TABLE ${Constants.TABLE_NAME} (" +
                "${Constants.KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Constants.KEY_NAME} TEXT," +
                "${Constants.KEY_PHONE_NUMBER} TEXT)"
        db?.execSQL(CREATE_TABLE_CONTACTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_NAME}")

        onCreate(db)
    }

    /*
    CRUD Operations - Create, Read, Update, Delete
     */
    fun addContact(contact: Contact) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(Constants.KEY_NAME, contact.name)
        values.put(Constants.KEY_PHONE_NUMBER, contact.phoneNumber)

        //insert to row
        db.insert(Constants.TABLE_NAME, null, values)
        db.close()
    }

    fun getContact(id: Int): Contact {
        val db = this.readableDatabase

        val cursor = db.query(Constants.TABLE_NAME,
            arrayOf(Constants.KEY_ID, Constants.KEY_NAME, Constants.KEY_PHONE_NUMBER),
            Constants.KEY_ID + "=?", arrayOf(id.toString()), null, null, null, null )

        cursor?.let {
            it.moveToFirst()
        }

        return  Contact(cursor.getString(0).toInt(), cursor.getString(1), cursor.getString(2))
    }

}