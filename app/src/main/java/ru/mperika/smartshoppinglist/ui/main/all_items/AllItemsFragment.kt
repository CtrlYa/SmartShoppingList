package ru.mperika.smartshoppinglist.ui.main.all_items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.ui.main.shopping_list.ShoppingListViewModel
import ru.mperika.smartshoppinglist.ui.main.shopping_list.ShoppingListViewModelFactory

class AllItemsFragment : Fragment() {
    private val TAG = "ALL ITEMS";
    companion object {
        fun newInstance() = AllItemsFragment()
    }

    private lateinit var viewModelFactory: AllItemsViewModelFactory
    private lateinit var viewModel: AllItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = activity?.let { AllItemsViewModelFactory(it.application) }!!
        viewModel = ViewModelProvider(this, viewModelFactory).get(AllItemsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.all_items_fragment, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.all_items_recycler)
        val allItemsAdapter = AllItemsAdapter()
        recyclerView.adapter = allItemsAdapter
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: ${it.size}")
            allItemsAdapter.setValues(it)
            allItemsAdapter.notifyDataSetChanged()
        })
        return root
    }

    fun addData(product: Product) {
        viewModel.addProduct(product)
    }

    fun updateAll() {
        viewModel.updateAllData()
    }
}