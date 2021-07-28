package com.example.shoeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,DATA_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATA_NAME = "sellersDatabase"
        private val TABLE_SELLER = "sellersTable"
        private val KEY_SPINNER = "spinner"
        private val KEY_RETAILPRICE = "retailprice"
        private val KEY_CONTACTNUMBER = "contactnumber"
        private val KEY_NAME = "name"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE =
                ("CREATE TABLE " + TABLE_SELLER + "(" + KEY_SPINNER + " TEXT," + KEY_RETAILPRICE +
                        " TEXT," + KEY_CONTACTNUMBER + " TEXT," + KEY_NAME + " TEXT" + ")")
        //EXECUTE query
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_SELLER)
        onCreate(db)
    }
   //save data
    fun addSeller(sqlLiteModel: SqlliteModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_RETAILPRICE, sqlLiteModel.retailprices)
        contentValues.put(KEY_CONTACTNUMBER, sqlLiteModel.contactnumbers)
        contentValues.put(KEY_NAME, sqlLiteModel.names)
       //query to insert to db
        val success: Long = db.insert(TABLE_SELLER, null, contentValues)
        db.close()
        return success
    }
    //function to view detail
    fun readData(): List<SqlliteModel> {
        //GET RESIZABLE ARRAY
        val userArray: ArrayList<SqlliteModel> = ArrayList<SqlliteModel>()
        //define fetch
        val selectQuery = "SELECT * FROM $TABLE_SELLER"
        //define what the
        val db = this.readableDatabase
        //reading our data
        var cursor: Cursor? = null
        //declare try catcch
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var spinner: String
        var sellerretailprice: String
        var sellercontactnumber: String
        var sellername: String
        //using cursor to pick record
        if (cursor.moveToFirst()) {

            do {
                sellerretailprice = cursor.getString(cursor.getColumnIndex("retailprice"))
                sellercontactnumber = cursor.getString(cursor.getColumnIndex("contactnumber"))
                sellername = cursor.getString(cursor.getColumnIndex("name"))
                //taking me to model class
                val seller =SqlliteModel( retailprices = sellerretailprice, contactnumbers = sellercontactnumber, names = sellername)
                userArray.add(seller)

            } while (cursor.moveToNext())
        }
        return userArray
    }
}