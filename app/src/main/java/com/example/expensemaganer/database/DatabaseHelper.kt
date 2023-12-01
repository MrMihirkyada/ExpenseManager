package com.example.expensemaganer.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.expensemaganer.Model.ModeModel
import com.example.expensemaganer.Model.Model
import com.example.expensemaganer.Model.expenseModel
import java.util.Locale

class DatabaseHelper(context: Context, name : String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version)
{

    lateinit var category : Locale.Category
    override fun onCreate(db: SQLiteDatabase?)
    {
        var sql = "create table categoryTb(categoryId integer primary key autoincrement,category text)"
        db?.execSQL(sql)

        var sql2 = "create table modeTb(modeId integer primary key autoincrement,mode text)"
        db?.execSQL(sql2)

        var sql1 = "create table expenseTb(expenseId integer primary key autoincrement,incomeExpensetype integer,amount integer,category text,date text,time text,note text,mode text)"
        db?.execSQL(sql1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {

    }

    fun insertData(category : String)
    {
        var db = writableDatabase

        var c = ContentValues()
        c.put("category",category)

        db.insert("categoryTb",null,c)
    }

    fun insertpayementmode(mode : String)
    {
        var db = writableDatabase

        var c = ContentValues()
        c.put("mode",mode)

        db.insert("modeTb",null,c)
    }

    fun insertExpenseData(
        incomeExpensetype: Int,
        amount: Int,
        category: String,
        date: String,
        time: String,
        note: String,
        mode : String,
    )
    {
        var db = writableDatabase

        var c = ContentValues()
        c.put("incomeExpensetype",incomeExpensetype)
        c.put("amount",amount)
        c.put("category",category)
        c.put("date",date)
        c.put("time",time)
        c.put("note",note)
        c.put("mode",mode)

        var result = db.insert("expenseTb", null, c)
        Log.e("TAG", "initview: " + result)
    }

    fun displayRecord(): ArrayList<Model>
    {
        var list = ArrayList<Model>()
        list.clear()
        var db=readableDatabase
        var sql="select * from categoryTb"
        var cursor : Cursor =db.rawQuery(sql,null)

        if(cursor.moveToFirst())
        {
            do
            {
                var id = cursor.getInt(0)
                var category = cursor.getString(1)

                Log.e("TAG", "displayRecord: "+category)
                list.add(Model(id,category))

            }while (cursor.moveToNext())
        }
        return list
    }

    fun displaymodeRecord(): ArrayList<ModeModel>
    {
        var list = ArrayList<ModeModel>()
        list.clear()
        var db=readableDatabase
        var sql="select * from modeTb"
        var cursor : Cursor =db.rawQuery(sql,null)

        if(cursor.moveToFirst())
        {
            do
            {
                var id = cursor.getInt(0)
                var mode = cursor.getString(1)

                Log.e("TAG", "displayRecord: "+mode)
                list.add(ModeModel(id,mode))

            }while (cursor.moveToNext())
        }
        return list
    }
    fun displayExpenseRecord(): ArrayList<expenseModel>
    {
        var list = ArrayList<expenseModel>()
        var db=readableDatabase
        var sql1="select * from expenseTb"
        var cursor : Cursor =db.rawQuery(sql1,null)

        if(cursor.moveToFirst())
        {
            do
            {
                var id = cursor.getInt(0)
                var incomeExpensetype = cursor.getInt(1)
                var amount = cursor.getInt(2)
                var category = cursor.getString(3)
                var date = cursor.getString(4)
                var time = cursor.getString(5)
                var note = cursor.getString(6)
                var mode = cursor.getString(7)

                Log.e("TAG", "displayRecord: "+ id+incomeExpensetype+amount+category+date+time+note+mode)
                list.add(expenseModel(id,incomeExpensetype,amount,category, date, time, note,mode))

            }while(cursor.moveToNext())
        }
        return list
    }
}
