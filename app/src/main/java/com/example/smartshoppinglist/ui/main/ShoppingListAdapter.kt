package com.example.smartshoppinglist.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.smartshoppinglist.R

class ShoppingListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private final val layoutInflater = LayoutInflater.from(context);
    private val values = mutableListOf("1", "2", "3", "4")

    inner class ListItemHolder(view: View) : ViewHolder(view) {
        val itemInfo = view.findViewById<TextView>(R.id.item_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = layoutInflater.inflate(R.layout.shopping_list_item, parent, false)
        return ListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ListItemHolder
        holder.itemInfo.text = values[position];
    }

    override fun getItemCount(): Int {
        return values.size
    }
}