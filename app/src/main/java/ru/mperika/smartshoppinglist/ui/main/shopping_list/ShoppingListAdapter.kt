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
        private val itemInCart: CheckBox = view.findViewById(R.id.inCartBox)
        val itemHeader: TextView = view.findViewById(R.id.headerTextView)
        val itemDescription: TextView = view.findViewById(R.id.detailTextView)
        val itemQuantity: TextView = view.findViewById(R.id.quantityText)

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
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_list_item, parent, false)
        return ListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        holder.itemHeader.text = values[position].productUserName
        holder.itemDescription.text = "Бренд: ${values[position].productBrand} Категория: ${values[position].category.catName}"
        holder.itemQuantity.text = values[position].quantity.toString()
    }

    override fun getItemCount() = values.size

    fun setValues(vals: MutableList<Product>) {
        values.clear()
        values.addAll(vals)
    }

}