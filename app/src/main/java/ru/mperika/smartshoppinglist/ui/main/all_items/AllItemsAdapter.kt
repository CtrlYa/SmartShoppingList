package ru.mperika.smartshoppinglist.ui.main.all_items

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.data.Product

class AllItemsAdapter : RecyclerView.Adapter<AllItemsAdapter.ListItemHolder>() {

    private val values: MutableList<Product> = mutableListOf()

    inner class ListItemHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ai_image)
        val headerText: TextView = view.findViewById(R.id.ai_header)
        val descText: TextView = view.findViewById(R.id.ai_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_items_element, parent, false)
        return ListItemHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder.headerText.text = values[position].productUserName
        holder.descText.text = "Бренд: ${values[position].productBrand}\n" +
                "Категория: ${values[position].category.catName}"
    }

    override fun getItemCount(): Int = values.size

    fun setValues(vals: MutableList<Product>) {
        values.clear()
        values.addAll(vals)
    }
}