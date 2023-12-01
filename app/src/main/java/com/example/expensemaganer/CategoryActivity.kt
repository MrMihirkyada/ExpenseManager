package com.example.expensemaganer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensemaganer.Model.Model
import com.example.expensemaganer.database.DatabaseHelper
import com.example.expensemaganer.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity()
{

    lateinit var binding : ActivityCategoryBinding
    lateinit var  C : String
    lateinit var databaseHelper : DatabaseHelper
    private var categorylist = ArrayList<Model>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview()
    {
        databaseHelper = DatabaseHelper(this,"Category.db",null,1)

        binding.imgback.setOnClickListener{
            onBackPressed()
        }


        binding.btnaddCategory.setOnClickListener {

            var category = binding.edtCategory.text.toString()                                                                                                     

            if(category.isNotEmpty())
            {
                databaseHelper.insertData(category)
                var i = Intent(this@CategoryActivity,DashboradActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Data add Successfully", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Please enter the Category Name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnaddMode.setOnClickListener {
            var mode = binding.edtMode.text.toString()

            if(mode.isNotEmpty())
            {
                databaseHelper.insertpayementmode(mode)
                var i = Intent(this@CategoryActivity,DashboradActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Data add Successfully", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Please enter the payement mode Name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}