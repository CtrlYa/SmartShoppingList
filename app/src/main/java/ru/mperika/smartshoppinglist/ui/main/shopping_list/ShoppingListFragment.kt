package ru.mperika.smartshoppinglist.ui.main.shopping_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.data.Product

class ShoppingListFragment() : Fragment() {

    private val TAG = "ShoppingList";
    private lateinit var viewModelFactory: ShoppingListViewModelFactory
    private lateinit var shoppingListViewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = activity?.let { ShoppingListViewModelFactory(it.application) }!!
        shoppingListViewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.shopping_list)
        val shoppingListAdapter = ShoppingListAdapter()
        recyclerView.adapter = shoppingListAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        shoppingListViewModel.getData().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: ${it.size}")
            shoppingListAdapter.setValues(it)
            shoppingListAdapter.notifyDataSetChanged()
        })

        return root
    }

    fun addData(product: Product) {
        shoppingListViewModel.addProduct(product)
    }

    fun updateAll() {
        shoppingListViewModel.updateAllData()
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): ShoppingListFragment {
            return ShoppingListFragment()
        }
    }
}