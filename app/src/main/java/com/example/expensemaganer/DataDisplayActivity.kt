package com.example.expensemaganer

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemaganer.Adapter.CategoryAdapter
import com.example.expensemaganer.Model.Model
import com.example.expensemaganer.database.DatabaseHelper
import com.example.expensemaganer.databinding.ActivityDataDisplayBinding

class DataDisplayActivity : AppCompatActivity()
{
    lateinit var databaseHelper : DatabaseHelper
    lateinit var binding : ActivityDataDisplayBinding
    var id : Int = 0
    lateinit var  C : String
    private var categorylist = ArrayList<Model>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_display)
        binding = ActivityDataDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview()
    {

        databaseHelper = DatabaseHelper(this,"Category.db",null,1)

        categorylist = databaseHelper.displayRecord()
        binding.spncategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                 C = categorylist[position].category
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        var adapter = CategoryAdapter(this,categorylist)

        binding.spncategory.adapter= adapter

        adapter.notifyDataSetChanged()
    }
}