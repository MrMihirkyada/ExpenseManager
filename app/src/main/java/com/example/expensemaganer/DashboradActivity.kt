package com.example.expensemaganer

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemaganer.databinding.ActivityDashboradBinding

class DashboradActivity : AppCompatActivity()
{
    lateinit var binding: ActivityDashboradBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        binding = ActivityDashboradBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE)

        initview()

    }

    private fun initview()
    {

        binding.btnCategory.setOnClickListener{
            var i = Intent(this@DashboradActivity, CategoryActivity::class.java)
            startActivity(i)
        }

        binding.btnExpences.setOnClickListener {

            var i = Intent(this@DashboradActivity, IncomeExpensesActivity::class.java)
            i.putExtra("Expense",1)
            startActivity(i)
        }

        binding.btnIncome.setOnClickListener {

            var i = Intent(this@DashboradActivity, IncomeExpensesActivity::class.java)
            i.putExtra("Expense",0)
            startActivity(i)
        }

        binding.btnTransaction.setOnClickListener {
            var i = Intent(this@DashboradActivity, TransactionActivity::class.java)
            startActivity(i)
        }

        binding.opendatepicker.setOnClickListener {
            binding.datepicker.init(
                2023,
                0,
                1,
                DatePicker.OnDateChangedListener { view, year, month, dayofMonth ->
                    val selectedDate = "$dayofMonth/${month + 1}/$year"
                }
            )

            opendatepicker()
        }

        binding.imgmenu.setOnClickListener {
            binding.drawer.openDrawer(binding.navigationview)
        }

        binding.lnrhome.setOnClickListener {
            binding.drawer.closeDrawer(binding.navigationview)
        }

        binding.lnrcalender.setOnClickListener {

            binding.datepicker.init(
                2023,
                0,
                1,
                DatePicker.OnDateChangedListener { view, year, month, dayofMonth ->
                    val selectedDate = "$dayofMonth/${month + 1}/$year"
                }
            )
            opendatepicker()
        }

        binding.lnrCategory.setOnClickListener {

            var i = Intent(this@DashboradActivity,CategoryActivity::class.java)
            startActivity(i)
        }

        binding.lnrpaymentmode.setOnClickListener {

            var i = Intent(this@DashboradActivity,CategoryActivity::class.java)
            startActivity(i)
        }

    }

    private fun opendatepicker()
    {
        var initialYear = 2023
        var initialMonth = 0
        var initialDay = 1

        var datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                var selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(this, "selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
            },

            initialYear,
            initialMonth,
            initialDay
        )

        datePickerDialog.show()
    }
}