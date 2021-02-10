package ru.mperika.smartshoppinglist.ui.main.shopping_list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.data.Product

class ShoppingListAdapter() : RecyclerView.Adapter<ShoppingListAdapter.ListItemHolder>() {

    private val values: MutableList<Product> = mutableListOf()

    inner class ListItemHolder(view: View) : ViewHolder(view) {
        val itemInCart = view.findViewById<CheckBox>(R.id.inCartBox)
        val itemHeader = view.findViewById<TextView>(R.id.headerTextView)
        val itemDescription = view.findViewById<TextView>(R.id.detailTextView)

        init {
            itemInCart.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    itemHeader.apply {
                        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    }
                    itemDescription.apply {
                        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    }
                } else {

                    itemHeader.apply {
                        paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                    itemDescription.apply {
                        paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_list_item, parent, false)
        return ListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        holder.itemHeader.text = values[position].productUserName;
    }

    override fun getItemCount() = values.size

    fun setValues(vals: MutableList<Product>) {
        values.clear()
        values.addAll(vals)
    }

}