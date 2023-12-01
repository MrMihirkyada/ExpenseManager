package com.example.expensemaganer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.expensemaganer.IncomeExpensesActivity
import com.example.expensemaganer.Model.ModeModel
import com.example.expensemaganer.R

class ModeAdapter(var dataDisplayActivity: IncomeExpensesActivity, var modelist: ArrayList<ModeModel>) : BaseAdapter()
{
    override fun getCount(): Int
    {
        return  modelist.size
    }

    override fun getItem(p0: Int): Any?
    {
        return null
    }

    override fun getItemId(p0: Int): Long
    {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View
    {
        val v : View = LayoutInflater.from(dataDisplayActivity).inflate(R.layout.mode_item,null)
        val mode = v.findViewById<TextView>(R.id.txtname)
        mode.text = modelist[p0].mode
        return v
    }
}
