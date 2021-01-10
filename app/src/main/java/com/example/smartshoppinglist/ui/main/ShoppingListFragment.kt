package com.example.smartshoppinglist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppinglist.R

class ShoppingListFragment() : Fragment() {

    private lateinit var shoppingListViewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        shoppingListViewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)
//                .apply {
//            setIndex(arguments?.getInt(ShoppingListFragment.ARG_SECTION_NUMBER) ?: 1)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_shopping_list, container, false)
//        val recyclerView = root.findViewById<RecyclerView>(R.id.shopping_list)
//        val shoppingListAdapter = context?.let { ShoppingListAdapter(it) }
//        recyclerView.adapter = shoppingListAdapter

        return root
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
        fun newInstance(sectionNumber: Int): FridgeFragment {
            return FridgeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}