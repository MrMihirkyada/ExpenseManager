package com.example.expensemaganer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemaganer.R
import com.example.expensemaganer.Model.expenseModel

class TransactionAdapter(
    var datalist: ArrayList<expenseModel>,
//    var data: Int?,
) : RecyclerView.Adapter<TransactionAdapter.MyviewHolder>()
{
    class MyviewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var txtchange: TextView = view.findViewById(R.id.txtchange)
        var txtamount: TextView = view.findViewById(R.id.txtamount)
        var txtdate: TextView = view.findViewById(R.id.txtdate)
        var txttime: TextView = view.findViewById(R.id.txttime)
        var txtcategory: TextView = view.findViewById(R.id.txtcategory)
        var txtnote: TextView = view.findViewById(R.id.txtnote)
        var txtpaymentmethod: TextView = view.findViewById(R.id.txtpaymentmethod)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapter.MyviewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item,parent,false)
        var holder = MyviewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: TransactionAdapter.MyviewHolder, position: Int)
    {
        holder.txtchange.text = datalist.get(position).typechange.toString()
        holder.txtamount.text = datalist.get(position).amount.toString()
        holder.txtcategory.text = datalist.get(position).category
        holder.txtdate.text = datalist.get(position).date
        holder.txttime.text = datalist.get(position).time
        holder.txtnote.text = datalist.get(position).note
        holder.txtpaymentmethod.text = datalist.get(position).mode

    }

    override fun getItemCount(): Int
    {
        return datalist.size
    }

}