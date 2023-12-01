package com.example.expensemaganer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemaganer.IncomeExpensesActivity
import com.example.expensemaganer.Model.Model
import com.example.expensemaganer.R

class ExpenseAdapter(var dataDisplayActivity: IncomeExpensesActivity, var categorylist: ArrayList<Model>) : BaseAdapter()
{
    override fun getCount(): Int {
        return  categorylist.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View
    {
        val v : View = LayoutInflater.from(dataDisplayActivity).inflate(R.layout.category_item,null)
        val Category = v.findViewById<TextView>(R.id.txtname)
        Category.text = categorylist[p0].category
        return v
    }
}