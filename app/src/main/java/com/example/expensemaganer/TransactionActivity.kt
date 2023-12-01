package com.example.expensemaganer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemaganer.Adapter.TransactionAdapter
import com.example.expensemaganer.Model.expenseModel
import com.example.expensemaganer.database.DatabaseHelper
import com.example.expensemaganer.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity()
{
    lateinit var databaseHelper : DatabaseHelper
    lateinit var myAdapter: TransactionAdapter
    var id : Int = 0
    var Expense = 1
    var Income  = 0
    lateinit var db : DatabaseHelper
    var incomeExpensetype : Int? = null
    var datalist = ArrayList<expenseModel>()


    lateinit var binding:ActivityTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview()
    {
        db  = DatabaseHelper(this, "Category.db", null, 1)

        var data = intent.getIntExtra("Income",1)

        if (incomeExpensetype == 1)
        {
             incomeExpensetype = data
            Log.e("TAG", "initview: "+incomeExpensetype)
        }
        else
        {
            incomeExpensetype = data
            Log.e("TAG", "initview: "+incomeExpensetype)
        }

        datalist= db.displayExpenseRecord()

        myAdapter = TransactionAdapter(datalist)

        val layoutManager = LinearLayoutManager(this@TransactionActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.layoutManager = layoutManager

        binding.recyclerview.adapter = myAdapter
    }
}

