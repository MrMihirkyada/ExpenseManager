package com.example.expensemaganer

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemaganer.Adapter.ExpenseAdapter
import com.example.expensemaganer.Adapter.ModeAdapter
import com.example.expensemaganer.Model.ModeModel
import com.example.expensemaganer.Model.Model
import com.example.expensemaganer.database.DatabaseHelper
import com.example.expensemaganer.databinding.ActivityExpensesBinding
import java.util.Calendar
import java.util.Date
import java.util.Locale

class IncomeExpensesActivity : AppCompatActivity() {
    lateinit var C: String
    lateinit var M: String

    lateinit var databaseHelper: DatabaseHelper
    lateinit var binding: ActivityExpensesBinding
    var Expense = 1
    var Income = 0
    var incomeexpense = 0
    var AddExpense = "Expense"
    var AddIncome = "Income"
    private var categorylist = ArrayList<Model>()
    private var modelist = ArrayList<ModeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpensesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        var data = intent.getIntExtra("Expense", 1)

        if (data == 1) {
            binding.rbExpense.isChecked = true
            binding.txtChange.text = "Add Expense"
        } else {
            binding.rbIncome.isChecked = true
            binding.txtChange.text = "Add Income"
        }

        binding.txtTime.text = "${getCurrentTime()}"

        binding.txtDate.text = "${getCurrentDate()}"
        databaseHelper = DatabaseHelper(this, "Category.db", null, 1)

        binding.imgback.setOnClickListener {
            onBackPressed()
        }

        binding.txtDate.setOnClickListener {

            opendatepicker()
        }

        binding.txtTime.setOnClickListener {
            opentimepicker()
        }

        binding.datepicker.init(
            2023,
            0,
            1,
            DatePicker.OnDateChangedListener { view, year, month, dayofMonth ->
                val selectedDate = "$dayofMonth/${month + 1}/$year"
                binding.txtDate.text = selectedDate
            }
        )

        databaseHelper = DatabaseHelper(this, "Category.db", null, 1)

        categorylist = databaseHelper.displayRecord()
        binding.spnspinnercategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                C = categorylist[position].category
                Log.e("TAG", "onItemSelected:" + C)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        modelist = databaseHelper.displaymodeRecord()
        binding.spnspinnerMode.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {

                    M = modelist[position].mode
                    Log.e("TAG", "onItemSelected: " + M)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }


        var adapter = ExpenseAdapter(this@IncomeExpensesActivity, categorylist)
        binding.spnspinnercategory.adapter = adapter

        var modeAdapter = ModeAdapter(this@IncomeExpensesActivity, modelist)
        binding.spnspinnerMode.adapter = modeAdapter

        adapter.notifyDataSetChanged()

//        var incomeExpenses = intent.getStringExtra("incomeExpensetype")

        binding.btnSubmit.setOnClickListener {

            var i = Intent(this@IncomeExpensesActivity, TransactionActivity::class.java)

            if (binding.rbExpense.isSelected == true)
            {
                i.putExtra("Income", Expense)
                incomeexpense = Expense
            }
            else if(binding.rbIncome.isSelected == true)
            {
                i.putExtra("Income", Income)
                incomeexpense = Income
            }

            var incomeExpensetype = data.toString().toInt()
            var amount = binding.edtAmount.text.toString().toInt()
            var category = C
            var date = binding.txtDate.text.toString()
            var time = binding.txtTime.text.toString()
            var note = binding.edtNote.text.toString()
            var mode = M

            databaseHelper.insertExpenseData(
                incomeExpensetype,
                amount,
                category,
                date,
                time,
                note,
                mode
            )
            Log.e(
                "TAG",
                "initview: " + incomeExpensetype + " " + amount + " " + category + " " + date + " " + time + " " + note + " " + mode
            )

            startActivity(i)
            finish()
        }
    }

    private fun opendatepicker() {
        var initialYear = 2023
        var initialMonth = 0
        var initialDay = 1

        var datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                var selectedDate = "Date :-$dayOfMonth/${month + 1}/$year"
                binding.txtDate.text = selectedDate
                Toast.makeText(this, "selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            initialYear,
            initialMonth,
            initialDay
        )
        datePickerDialog.show()
    }

    private fun opentimepicker() {
        var calender = Calendar.getInstance()
        var hour = calender.get(Calendar.HOUR_OF_DAY)
        var minute = calender.get(Calendar.MINUTE)

        var timePickerDialog = TimePickerDialog(this, { view, hourOfDay, minute ->
            var selectedTime =
                String.format(Locale.getDefault(), "Time :-%02d:%02d", hourOfDay, minute)
            binding.txtTime.text = selectedTime
        }, hour, minute, false)

        timePickerDialog.show()
    }

    private fun getCurrentTime(): String? {
        var dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        var currentTime = dateFormat.format(Date())
        return currentTime
    }

    private fun getCurrentDate(): String? {
        var dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        var currentDate = dateFormat.format(Date())
        return currentDate
    }
}