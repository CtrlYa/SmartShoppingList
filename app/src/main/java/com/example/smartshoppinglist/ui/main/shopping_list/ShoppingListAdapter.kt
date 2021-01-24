package com.example.smartshoppinglist.ui.main.shopping_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.smartshoppinglist.R

class ShoppingListAdapter(private val values: MutableList<String>) : RecyclerView.Adapter<ShoppingListAdapter.ListItemHolder>() {

    inner class ListItemHolder(view: View) : ViewHolder(view) {
        val itemInfo = view.findViewById<TextView>(R.id.item_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_list_item, parent, false)
        return ListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        holder.itemInfo.text = values[position];
    }

    override fun getItemCount() = values.size

    fun setValues(vals: MutableList<String>) {
        values.clear()
        values.addAll(vals)
    }

}